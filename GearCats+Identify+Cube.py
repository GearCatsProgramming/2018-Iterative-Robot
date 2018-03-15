
# coding: utf-8

# In[1]:

#importing some useful packages
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np
import cv2
get_ipython().magic('matplotlib inline')

print('All modules imported.')


# In[2]:

image=mpimg.imread('CarND-Term1-Starter-Kit-Test/Renegade-Cube.jpeg')
imgplot = plt.imshow(image)
plt.show()


# In[3]:

import math

def grayscale(img):
    """Applies the Grayscale transform
    This will return an image with only one color channel
    but NOTE: to see the returned image as grayscale
    (assuming your grayscaled image is called 'gray')
    you should call plt.imshow(gray, cmap='gray')"""
    return cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)
    # Or use BGR2GRAY if you read an image with cv2.imread()
    # return cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    
def canny(img, low_threshold, high_threshold):
    """Applies the Canny transform"""
    return cv2.Canny(img, low_threshold, high_threshold)

def gaussian_blur(img, kernel_size):
    """Applies a Gaussian Noise kernel"""
    return cv2.GaussianBlur(img, (kernel_size, kernel_size), 0)

def region_of_interest(img, vertices):
    """
    Applies an image mask.
    
    Only keeps the region of the image defined by the polygon
    formed from `vertices`. The rest of the image is set to black.
    """
    #defining a blank mask to start with
    mask = np.zeros_like(img)   
    
    #defining a 3 channel or 1 channel color to fill the mask with depending on the input image
    if len(img.shape) > 2:
        channel_count = img.shape[2]  # i.e. 3 or 4 depending on your image
        ignore_mask_color = (255,) * channel_count
    else:
        ignore_mask_color = 255
        
    #filling pixels inside the polygon defined by "vertices" with the fill color    
    cv2.fillPoly(mask, vertices, ignore_mask_color)
    
    #returning the image only where mask pixels are nonzero
    masked_image = cv2.bitwise_and(img, mask)
    return masked_image


def draw_lines(img, lines, color=[255, 0, 0], thickness=2):
    """
    NOTE: this is the function you might want to use as a starting point once you want to 
    average/extrapolate the line segments you detect to map out the full
    extent of the lane (going from the result shown in raw-lines-example.mp4
    to that shown in P1_example.mp4).  
    
    Think about things like separating line segments by their 
    slope ((y2-y1)/(x2-x1)) to decide which segments are part of the left
    line vs. the right line.  Then, you can average the position of each of 
    the lines and extrapolate to the top and bottom of the lane.
    
    This function draws `lines` with `color` and `thickness`.    
    Lines are drawn on the image inplace (mutates the image).
    If you want to make the lines semi-transparent, think about combining
    this function with the weighted_img() function below
    """
    for line in lines:
        for x1,y1,x2,y2 in line:
            cv2.line(img, (x1, y1), (x2, y2), color, thickness)

def hough_lines(img, rho, theta, threshold, min_line_len, max_line_gap):
    """
    `img` should be the output of a Canny transform.
        
    Returns an image with hough lines drawn.
    """
    lines = cv2.HoughLinesP(img, rho, theta, threshold, np.array([]), minLineLength=min_line_len, maxLineGap=max_line_gap)
    line_img = np.zeros((img.shape[0], img.shape[1], 3), dtype=np.uint8)
    draw_lines(line_img, lines)
    return line_img

# Python 3 has support for cool math symbols.

def weighted_img(img, initial_img, α=0.8, β=1., γ=0.):
    """
    `img` is the output of the hough_lines(), An image with lines drawn on it.
    Should be a blank image (all black) with lines drawn on it.
    
    `initial_img` should be the image before any processing.
    
    The result image is computed as follows:
    
    initial_img * α + img * β + γ
    NOTE: initial_img and img must be the same shape!
    """
    return cv2.addWeighted(initial_img, α, img, β, γ)


# In[4]:

from ipywidgets import interact, interactive, fixed

# Define a function that thresholds the B-channel of LAB
# Use exclusive lower bound (>) and inclusive upper (<=), OR the results of the thresholds (B channel should capture
# yellows)
def lab_bthresh(img, thresh=(190,255)):
    # 1) Convert to LAB color space
    lab = cv2.cvtColor(img, cv2.COLOR_RGB2Lab)
    lab_b = lab[:,:,2]
    # don't normalize if there are no yellows in the image
    if np.max(lab_b) > 175:
        lab_b = lab_b*(255/np.max(lab_b))
    # 2) Apply a threshold to the L channel
    binary_output = np.zeros_like(lab_b)
    binary_output[((lab_b > thresh[0]) & (lab_b <= thresh[1]))] = 1
    # 3) Return a binary image of threshold result
    return binary_output
print('...')

def update(min_b_thresh, max_b_thresh):
    exampleImg_LBThresh = lab_bthresh(img, (min_b_thresh, max_b_thresh))
    # Visualize LAB B threshold
    f, (ax1, ax2) = plt.subplots(1, 2, figsize=(20,10))
    f.subplots_adjust(hspace = .2, wspace=.05)
    ax1.imshow(img)
    ax1.set_title('Unwarped Image', fontsize=30)
    ax2.imshow(exampleImg_LBThresh, cmap='gray')
    ax2.set_title('LAB B-channel', fontsize=30)

thresh = lab_bthresh(image, (75, 190))

# noise removal
kernel = np.ones((3,3),np.uint8)
opening = cv2.morphologyEx(thresh,cv2.MORPH_OPEN,kernel, iterations = 2)
# sure background area
sure_bg = cv2.dilate(opening,kernel,iterations=3)

imgslice = np.uint8(sure_bg)
img = canny(imgslice, 0, 5)
float_bg = np.float32(img)
dst = cv2.cornerHarris(float_bg,10,3,.04)
dst = cv2.dilate(dst,None)

# Threshold for an optimal value, it may vary depending on the image.
image[((dst > 0.01) & (dst <= dst.max()))]=0


f, (ax1, ax2) = plt.subplots(1, 2, figsize=(20,10))
f.subplots_adjust(hspace = .2, wspace=.05)
ax1.imshow(sure_bg)
ax1.set_title('Unwarped Image', fontsize=30)
ax2.imshow(dst)
ax2.set_title('LAB B-channel', fontsize=30)

print('...')



# In[5]:

def process_image(image):
    # NOTE: The output you return should be a color image (3 channel) for processing video below
    # TODO: put your pipeline here,
    # you should return the final output (image where lines are drawn on lanes)
    image = lab_bthresh(image, thresh=(50,198))
    imgslice = np.uint8(image)
    img = canny(imgslice, 0, 5)

    return result


# In[6]:

from multiprocessing import Queue

# setup for initial search
search_img = sure_bg[::16, ::16]

f, (ax1, ax2) = plt.subplots(1, 2, figsize=(20,10))
f.subplots_adjust(hspace = .2, wspace=.05)
ax1.imshow(sure_bg)
ax1.set_title('Unwarped Image', fontsize=30)
ax2.imshow(search_img)
ax2.set_title('LAB B-channel', fontsize=30)

x_size = search_img.shape[1]
y_size = search_img.shape[0]
#print(y_size, x_size)

lowest_y = y_size
highest_y = 0

#search starts at bottom middle
x_pos = x_size//2
y_pos = y_size
purple_count = 0
x_sum = 0
pixel = -1
first_purple_x = -1
first_purple_y = -1

for x in range(x_pos, 0, -1):
    for y in range(y_pos-1, 0, -1): 

        pixel = search_img[y][x]
        if(pixel == 0): 
            first_purple_x = x
            first_purple_y = y
            y=0
            x=0

if(first_purple_x==-1):
    for x in range(x_pos+1, x_size-1):
        for y in range(y_pos-1, 0, -1): 

            pixel = search_img[y][x]
            if(pixel == 0):
                first_purple_x = x
                first_purple_y = y
                y=0
                x=0
              

#if it's purple
root = (first_purple_y, first_purple_x)

#up
def up(point=(y_pos, x_pos)):
    return (point[0]-1, point[1])

#down
def down(point=(y_pos, x_pos)):
    return (point[0]+1, point[1])

#left
def left(point=(y_pos, x_pos)):
    return (point[0], point[1]-1)

#right
def right(point=(y_pos, x_pos)):
    return (point[0], point[1]+1)
                
to_see = Queue()
visited = set()

to_see.put(root)
#print(root)
print(to_see.empty())

purple_count = 0
x_sum = 0
while not to_see.empty():

    subtree_root = to_see.get()
    #print("printing subtree root")
    #print(subtree_root)

    #We found the node we wanted so stop and emit a path.
    if (search_img[subtree_root[0]][subtree_root[1]] == 1): 
        visited.add(subtree_root)
        #print("subtree root yellow")
        #print(subtree_root)
    else:
        if up(subtree_root) not in visited:
            #print("putting in up value")
            #print(up(subtree_root))
            to_see.put(up(subtree_root))
            visited.add(up(subtree_root))
        if down(subtree_root) not in visited:
            #print("putting in down value")
            #print(down(subtree_root))
            to_see.put(down(subtree_root))
            visited.add(down(subtree_root))

        if left(subtree_root) not in visited:
            #print("putting in left value")
            #print(left(subtree_root))
            to_see.put(left(subtree_root))
            visited.add(left(subtree_root))

        if right(subtree_root) not in visited:
            #print("putting in right value")
            #print(right(subtree_root))
            to_see.put(right(subtree_root))
            visited.add(right(subtree_root))

            
        x_sum += subtree_root[1]
        if(lowest_y>subtree_root[0]): 
            lowest_y = subtree_root[0]
        if(highest_y<subtree_root[0]):
            highest_y = subtree_root[0]
        
        purple_count+=1
        print("incrementing purple count by 1")
    
        visited.add(subtree_root)

print(first_purple_x, first_purple_y)
print("purple_count")
print(purple_count)
print("sum of x")
print(x_sum)
print("x average")
print(x_sum/purple_count)
print("lowest y")
print(lowest_y)
print("highest y")
print(highest_y)

# check if the adjacent pixels are purple


# In[ ]:



