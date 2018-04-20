#!/usr/bin/env python3
import numpy as np
import cv2
import sys

def Canny(img, min, max):
	edge = cv2.Canny(img, min, max)
	return edge

cap = cv2.VideoCapture(0)

min = int(sys.argv[1])
max = int(sys.argv[2])

while True:
	_, img = cap.read()
	cv2.imshow('Original', cv2.resize(img, None, fx=0.25, fy=0.25))
	img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	img = cv2.GaussianBlur(img, (9, 9), 0)
	img = cv2.Canny(img, min, max)
	_, img = cv2.threshold(img, 70, 255, cv2.THRESH_BINARY_INV)
	img = cv2.resize(img, None, fx=0.25, fy=0.25)
	cv2.imshow('Edges', img)
	if cv2.waitKey(1) == ord('q'):
		cap.release()
		cv2.destroyAllWindows()
		break
