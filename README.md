# ListView
Displayed movie data(image,title, description, rating) in a list view with clickable items.

Task 1: Each row of the ListView should contain at least the following information: the image of movie, title, description, rating, and a CheckBox. You can design your own layout. There should be three buttons above the ListView (see Figure 1). You need to implement the following functionalities:
a) Get the ListView and BaseAdapter to work.
b)  Your code should use the ViewHolder pattern to optimize the performance. If your ListView does not scroll smoothly, it is very likely that you are not using the ViewHolder pattern.
c)  The odd rows and the even rows should look slightly different (you can decide the difference). You can achieve this by customizing BaseAdapter’s getView() method.
d) When a list item is clicked, the item’s CheckBox will be toggled. You can allow multiple CheckBoxes to be checked.
e)  When the “Select All” button is clicked, all the CheckBoxes will be checked. When the “Clear All” button is clicked, all the CheckBoxes will be unchecked.
f)  When the “Delete” button is clicked, all the selected movies will be deleted from the list.
g)  When a list item is long clicked, that item will be duplicated and placed right below the current item (i.e., a new movie item with duplicated content will appear).
h)  Make the first items unclickable (sometimes, a movies may not be available).


Task 2: GridView
Load the movie data into a GridView When users click on each item, just show which one is selected (e.g. using Toast). You can decide what to put in each grid cell; there is no need to put all the information into the grid cell, but the image of the movie is required.
