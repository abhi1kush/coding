set title "Insert Operations vs Height of Tree"
set xlabel "Number of Insert Operations"
set ylabel "Height"

#set xrange [0:5000]
#set yrange [0:50]

set xtic auto
set ytic auto
set grid

set terminal png 
set output "insert_height_exp.png" ; 
plot "insert_exp.txt" using 1:2 title "BST" with lines lt rgb "blue","insert_exp.txt" using 1:3 title "AVL Tree" with lines lt rgb "green","insert_exp.txt" using 1:4 title "Red Black Tree" with lines lt rgb "red";
set term x11

