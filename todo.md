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
  - salami
  - bacon
  - sausage
  - apricot swirl
  - barbecue swirl
  
When the corresponding menu item is selected the player can click/touch
to place the Topping on the pizza. When the  first two toppings, 
sauce and cheese, are placed they replace the entire pizza base Sprite. 
The four meat topping, chicken, salami, bacon and sausage, will have a
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
      - ~~create Topping selectedTopping field~~
      - ~~create setSelectedTopping method~~
      - ~~call the Pizza.addTopping method when we click somewhere~~
      - ~~in touchDown method call selectedTopping.copy method to get
        a Topping to pass to pizza.addTopping~~
      - ~~create toggleSelectedTopping(toppingName) which sets selectedTopping
        to toppingName IF it is not already selected, NONE otherwise~~
  
  + in Pizza
      - ~~a toppingArray field to hold Toppings~~
      - ~~initialise topplingArray with a base topping~~
      - ~~create addTopping method which takes Topping object and adds it to
        toppingArray~~        
      - ~~call PizzaScreen.toggleSelectedTopping method when a menu item is 
        checked~~
      - ~~create Topping noneTopping to handle NONE selectedTopping case~~
        
  + in ToppingMenu
      - ~~create addMenuItem to add a new TextButton item to table and to
        the buttonGroup~~
      - add eight menu items to menuItemList on construction
      
  + ~~create Topping.class:~~snasnasn
      - ~~float x, y, rotation~~
      - ~~Constants.ToppingName toppingName~~
      - ~~Sprite sprite~~
      - ~~draw method~~
      
  + Topping.class:
      - ~~add copy method which returns an identical Topping.class object~~
      - ~~remove x, y, rotation fields and alter getters/setters to access the
        Sprite field~~
        
  + ~~centre selected Topping to mouse~~
  
  + ~~write special cases for sauce and cheese Toppings~~
      - ~~draw smaller sprite when selectedTopping~~
      - ~~always the same x,y~~
      - ~~always in toppingArray\[0\]~~
  
  + ~~don't plot selectedTopping when outside pizza area~~
  
  + ~~don't add selectedTopping when outside pizza area~~
  
  + ~~fix selectTopping showing at touchUp location on Android~~
  
  + ~~find appropriate size for buttons and fonts on phone screens~~
    
  + ~~create toppings menu layout~~
  
  + ~~create art assets for~~
      - ~~chicken~~
      - ~~sausage~~
      - ~~salami~~
      - ~~apricot swirl~~
      - ~~barbecue swirl~~
      
  + ~~add Topping types to ToppingMenu~~
      - ~~chicken~~
      - ~~sausage~~
      - ~~salami~~
      - ~~apricot swirl~~
      - ~~barbecue swirl~~
      
  + ~~use I18NBundle bundle for UI strings~~ 
  
  + ~~fix floating selectedSprite on Desktop/Web~~
      
  + ~~try to make base textures line up visually~~
  
  + complete the Pizza menu
      - Poor People Pizza Party header
      - undo button: pops last topping off stack or steps back through base 
        logic
      - photo button
      - COOK button
      
  + create a screen shot interface
      - ~~create platform-specific classes as described at~~
        <https://github.com/libgdx/libgdx/wiki/Interfacing-with-platform-specific-code>
      - ~~implement for desktop~~
      - implement for android
      - implement for webgl
  
  + implement base logic
      - can't go back to sauce after cheese
      - cheese requires sauce?
      - can't change base after other toppings added (length array > 1)
      
  + add Android touch offset
      - when touching and holding the selected topping should appear above
        finger position so it's actually visible
    
  + investigate asset manager
  
  + implement Cook and Serve buttons
      - option to serve to Bosses or Workers
  
  + create ServeBossScreen class
      - boss says either "who eats this rubbish" or "is this game about me?"
        and then fires you
        
  + create ServeWorkersScreen.class
      - it's a pizza party celebrate!
      - boss pops up and says "I can't believe you people eat this 
        mass-produced rubbish"
      - everyone fired
      
  + create TitleScreen class
      - pizza party, a party for pizza
      
  + do a Vector Android app icon
  