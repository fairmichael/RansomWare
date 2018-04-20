#!/usr/bin/env python3

import numpy as np
import cv2
import sys

def get_image(path):
	return cv2.imread(path)

def gray_image(image):
	return cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

def canny(image):
	image = gray_image(image)
	# print("\n[*] Canny (Get Edges)")
	# min = int(input("\nEnter Minimum Threshold: "))
	# max = int(input("\nEnter Maximum Threshold: "))
	min, max = (200, 200)
	return cv2.Canny(image, min, max)

def laplacian(image):
	return cv2.Laplacian(image, cv2.CV_64F)

def houghlines(image):
	try:
		im = image
		edges = canny(im)
		edges = cv2.GaussianBlur(edges, (7,7), 0)
		# print("\n[*] HoughLinesP (Draw Lines)")
		# minLineLength = int(input("\nEnter Minimum Line Length: "))
		# maxLineGap = int(input("\nEnter Maximum Line Gap: "))
		minLineLength, maxLineGap = (20, 10)
		lines = cv2.HoughLinesP(edges,1,np.pi/180,180,minLineLength,maxLineGap)
		for x1,y1,x2,y2 in lines[0]:
			cv2.line(edges,(x1,y1),(x2,y2),(255,255,255),3)
		return edges
	except:
		return image

def main():

	cap = cv2.VideoCapture(0)
	def render(title):
		while True:
			_, frame = cap.read()
			if title == 'Gray':
				image = gray_image(frame)
			elif title == 'Canny':
				image = canny(frame)
			elif title == 'Laplacian':
				image = laplacian(frame)
			else:
				image = houghlines(frame)
			cv2.imshow(title, image)
			if cv2.waitKey(1) == ord('q'):
				cap.release()
				break

	usage = '''
		Gray       > 1
		Canny 	   > 2
		Laplacian  > 3
		Houghlines > 4 
		
		'''
	print("Usage: {}".format(usage))
	ch = int(input("\n[+] Enter Choice: "))
	# file = input("\n[+] Enter Image Path: ")
	# image = get_image(file)
	
	if ch == 1:
		render('Gray')
	elif ch == 2:
		render('Canny')
	elif ch == 3:
		render('Laplacian')
	elif ch == 4:
		render('Houghlines')
	else:
		print('\nUsage {}\n'.format(usage))
		sys.exit(1)

	# if cv2.waitKey(0) == ord('q'):
	# 	cv2.destroyAllWindows()
	ch = input("\n[+] Would You Like To Continue (y/n): ")
	if ch.lower() == 'y':
		cv2.destroyAllWindows()
		main()
	cv2.destroyAllWindows()

main()
