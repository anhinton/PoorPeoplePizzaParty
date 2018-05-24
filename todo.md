# TODO

  + ~~work out why base image shows up wrong size on Android and fix~~
  + ~~create Pizza class to display pizza~~
  + ~~create Base class~~
  + ~~make Android icon/app name look better~~
  + ~~drop Base class, make part of Pizza~~
  + ~~create setBaseTopping() method~~
  + ~~call setBaseTopping() on touch~~
  
# creating Topping and ToppingMenuButton objects

The plan is to have eight toppings available from a UI menu which can
be placed on the pizza:
  - sauce
  - cheese
  - chicken
  - pepperoni
  - bacon
  - ham
  - apricot swirl
  - barbecue swirl
  
When the corresponding menu item is selected the player can click/touch
to place the Topping on the pizza. When the  first two toppings, 
sauce and cheese, are placed they replace the entire pizza base Sprite. 
The four meat topping, chicken, pepperoni, bacon and ham, will have a
location and rotation, and will be added to a topping array which is 
drawn in the order they were placed. The two sauces, apricot swirl and
barbecue swirl, will also be placed in the topping queue. However these 
will always have the same x,y location, centred on the base. They could
have a random rotation.

A Topping object for drawing on the PizzaScreen should have: 
  - int x,y: location to draw
  - float rotation
  - Sprite sprite to be plotted
  
There should be eight ToppingMenuButton objects, one for each topping in the 
list above. When a ToppingMenuButton is selected this should set the 
toppingSelected field in PizzaScreen to topping name. While a 
ToppingMenuItem is selected, clicking on the Pizza will add a Topping 
object to the array of Topping objects held in Pizza.

  + in PizzaScreen:
      - ~~create String toppingSelected field~~
      - ~~create setSelectedTopping method~~
      - call the Pizza.addTopping method when we click somewhere
  
  + in Pizza
      - a toppingList field to hold Toppings
      - initialise topplingList with a base topping
      - an addTopping method which takes a String selectedTopping.
        this adds a topping to toppingList, or in the base cases (base, 
        sauce, cheese) replaces the first/base topping
      - call PizzaScreen.setSelectedTopping method when a menu item is checked
      - call PizzaScreen.setSelectTopping(null) when no item is checked
        
  + in ToppingMenu
      - create menuItemList field to hold eight menu items
      - create addMenuItem to add a new menu item to the menuItemList
      - add eight menu items to menuItemList on construction
    
  + create toppings menu layout
  
  + investigate asset manager