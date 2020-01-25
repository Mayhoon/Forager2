cd /D "%~dp0"
move convert.png imageMagick
del result2.gif
cd imageMagick 
convert -dispose 3 -delay 1x50 -loop 1 convert.png -crop %2x%3 -interpolate Nearest -filter point +repage result2.gif
convert result2.gif[0-%1] result3.gif
move result3.gif ..
start ..

