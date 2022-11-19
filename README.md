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