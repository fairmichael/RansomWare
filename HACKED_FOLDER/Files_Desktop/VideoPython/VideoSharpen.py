#!/usr/bin/env python3

import numpy as np
import cv2

kernel = np.array([[-1, -1, -1], [-1, 9, -1], [-1, -1, -1]])

def render(image, title):
	cv2.imshow(title, image)

def sharpen(image):
	sharpened = cv2.filter2D(image, -1, kernel)
	return sharpened

cap = cv2.VideoCapture(0)

while True:
	_, frame = cap.read()
	sharpened_image = sharpen(frame)
	render(cv2.resize(frame, None, fx=0.50, fy=0.50), 'Original Image')
	render(cv2.resize(sharpened_image, None, fx=0.50, fy=0.50), 'Sharpened Image')
	cv2.moveWindow('Original Image', 0, 0)	
	cv2.moveWindow('Sharpened Image', int(sharpened_image.shape[1]/2), int(sharpened_image.shape[0]/2))
	
	key = cv2.waitKey(1)

	if key == ord('q'):
		cap.release()
		cv2.destroyAllWindows()
		break
