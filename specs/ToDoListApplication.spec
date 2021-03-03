ToDo List Application
==========================


Given Emty ToDo list
--------------
* When I write "buy some milk" to "todoInput" and press enter
* Then I should see "inputTextFirst" item in ToDo list


Given ToDo list with text item
--------------
* When I write "buy some milk" to "todoInput" and press enter
* Then I should see "inputTextFirst" item in ToDo list
* When I write "enjoy the assignment" to "todoInput" and press enter
* Then I should see "inputTextSecond" item in ToDo list


Given ToDo list with text item check
--------------
* When I write "buy some milk" to "todoInput" and press enter
* When I click on "firstTextCheckBox"
* Check if element "selectedItem" exists


Given ToDo list with marked item
--------------
* When I write "buy some milk" to "todoInput" and press enter
* When I click on "firstTextCheckBox"
* Then I should see "inputTextFirst" item in ToDo list

Given ToDo list with item
--------------
* When I write "rest for a while" to "todoInput" and press enter
* Hover Element "inputText"
* When I click on "deleteItem"
* Wait "1" seconds
* Check remove element "inputText"

Given ToDo list delete elements
--------------
* When I write "rest for a while" to "todoInput" and press enter
* When I write "drink water" to "todoInput" and press enter
* Hover Element "secondItem"
* Wait "1" seconds
* When I click on "secondItemRemove"
* Wait "3" seconds
* Check remove element "secondItem"