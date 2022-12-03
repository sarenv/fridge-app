# Fridge: A Personal Project

## Description

This is a fridge application, meant to simulate a fridge and the daily simple tasks that you can do with a fridge, *plus some extra features I added as enhancement*.

I have chosen this project because I recently decided to start cooking consistently this school year. The tasks that can be done in this application is meant to mirror the things I do every day, or wish I could do with my fridge.

This project was created mainly with my needs in mind, but as it is a fridge, this application can be **useful for the general population**, especially those who cook often and like to know what is in their fridge.


## User Stories:
- As a user, you can **add** specific food items into the fridge.
- As a user, you can **remove** specific food items from the fridge.
- As a user, you can **view** all the food items that are inside the fridge.
- As a user, you can **input** an ingredient and check if the fridge **contains** the given food item.
- As a user, you can remove **all expired** food items from the fridge at once.

**Phase 2 - Saving User Stories**

- As a user, I want to be able to save my fridge to file
- As a user, I want to be able to load my fridge from file *(where everytime the fridge is loaded, the expiration date for all food goes down by one.)*

**Phase 3 - GUI**

### Instructions for Grader

- You can generate the first required event related to adding Xs to a Y **(Adding a Food item to Fridge)** by clicking the "Add Food Item" button. Follow the three subsequent dialog pop-ups to input the Food's name, size and days before expiring respectively.
- You can generate the second required event related to adding Xs to a Y **(Removing a Food item from Fridge)** by clicking the "Remove a Food Item" button. Follow the dialog pop-up to input a food's name that the user want to remove.
- You can locate my visual component by looking at the top of my FridgeUI. It is a JProgress Bar that tells you the amount of Fridge Space left after each Action.
- You can save the state of my application by clicking on the "Save Fridge" button.
- You can reload the state of my application by clicking on the "Load Fridge" button.

**Phase 4 - Task 2**

```
Fri Dec 02 13:16:06 PST 2022
pie has been added to the fridge.
Fri Dec 02 13:16:34 PST 2022
cheese has been added to the fridge.
Fri Dec 02 13:16:45 PST 2022
milk has been added to the fridge.
Fri Dec 02 13:16:49 PST 2022
cheese has been removed from the fridge.
Fri Dec 02 13:16:57 PST 2022
Viewed the contents of the fridge.
Fri Dec 02 13:17:02 PST 2022
pie has been removed from the fridge.
Fri Dec 02 13:17:04 PST 2022
Viewed the contents of the fridge.
```

**Phase 4 - Task 3**

While I am happy that I completed all the phases of the project, there would have been some changes I would have made if I had more time.

For Phase 1, I initially planned to make my `Food` class an abstract class and add five extra classes (Meat, Dairy, Fruits and Veg etc.) on different types of food to implement that class. Each of these classes would have a unique foodSize and DaysBeforeExpire, so when adding food to the fridge, the user does not have to input the size and expiration date. All they have to do is input the name and the type of food.

For Phase 3, there were two main improvements that I wanted to make. First, I wanted to change how I organised my GUI. What I did was I added all of my JPanel and JButton(s) in a single GUI class called `FridgeUI`. My UML diagram shows this, where my FridgeUI does not call any Java Swing classes. Although the programme still works, it made the class too long, and if a user is reading my code for the first time, they would have a hard time understanding my GUI class and how the methods were implemented.

Another stylistic choice I would make is to use JPanels instead of a Pop-up Message Dialog for my “Add/Remove X to Y” user stories. An issue I have is when the user starts inputting the information for Food, if they quit the application without inputting all three prompts (Name, Size and Expiration Date), the application actually stops running. To rectify this, I should have made a new JPanel with JTextfield and call that instead. By doing this, I will have more control over the user’s input and the overall design of the GUI.