#!/usr/bin/env python3

import cv2
import numpy as np
from sys import argv, exit
from time import time

cap = cv2.VideoCapture(0)

def render(img, title):
	cv2.imshow(title, img)

def gaussianblur(img, kernel):
	image = cv2.GaussianBlur(img, (kernel,kernel), 0)
	image = cv2.resize(image, None, fx=0.25, fy=0.25)
	return image

if len(argv) < 2:
	print('\n[!] Provide The Blurr Intensity #\n')
	exit(1)

begin = time()
intense = int(argv[1])
temp = -1

while True:
	_, img = cap.read()
	if int(argv[1]) <= 0:
		render(img, 'Frame')
	else:
		blurred_image = gaussianblur(img, intense)
		if intense != temp:
			print('\nBlurring Intensity: {}\n'.format(intense))
		temp = intense
		render(blurred_image, 'Gaussian Blur {}'.format(intense))
		render(cv2.resize(cap.read()[1], None, fx=0.25, fy=0.25), 'Original')
		if time() - begin > 5:
			begin = time()
			cv2.destroyWindow('Gaussian Blur {}'.format(intense))
			if intense % 2 == 0:
				intense += 1
			else:
				intense += 2

	key = cv2.waitKey(1)
	if key == ord('q'):
		cv2.destroyAllWindows()
		cap.release()
		break
