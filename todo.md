# TODO
        
  + implement for HtmlDesktopCaptureIO
      - remove fancy timestamp code using SimpleDateFormat from
        Capture.fileName() as this does not seem to be available to Html.
        filename now uses TimeUtils.millis()
      
  + **BUG**: fix transparent swirl (through to postcard background) when swirl 
    is last placed
  
  + implement Cook screen
      - ~~countdown timer~~
      - ~~then show a cooked pizza and give option to serve to Bosses or 
        Workers~~
      - ~~take Pizza from PizzaScreen as argument~~
      - ~~implement Android back button logic~~
          - ~~go back to PizzaScreen with existing Pizza~~
      - implement Bosses and Workers button actions
  
  + create ServeBossScreen class
      - send here from CookScreen choice
      - boss says either "who eats this rubbish" or "is this game about me?"
        and then fires you
      - implement Android back button logic
        
  + create ServeWorkersScreen.class
      - send here from CookScreen choice
      - it's a pizza party celebrate!
      - boss pops up and says "I can't believe you people eat this 
        mass-produced rubbish"
      - everyone fired
      - implement Android back button logic
      
  + create TitleScreen class
      - pizza party, a party for pizza
      - change Android back button behaviour in PizzaScreen.class to go back 
        to here
      
  + do a Vector Android app icon
  
  + stop debugging for release
  
  + investigate desktop full screen and window resizing
  
## Done
      
  + ~~create a screen shot interface~~
      - ~~create platform-specific classes as described at~~
        <https://github.com/libgdx/libgdx/wiki/Interfacing-with-platform-specific-code>
        
  + ~~implement DesktopCaptureIO~~
      - ~~split Screenshot into Capture class and CaptureIO interface~~
      - ~~generate sensible timestamped filename~~
          - ~~Capture.fileName() returns filename~~
          - ~~CaptureIO instances use Capture.fileName() in savePizza() to
            save to sensible location~~
            
  + ~~implement AndroidDesktopCaptureIO~~
          - ~~save PNG file to `{Environment.DIRECTORY_PICTURES}/PoorPeople` 
            directory~~
          - ~~**NB:** needed to drop Android target SDK back to 20 (Android 4.4) 
            to avoid new Android permission model introduced in Android 6.0~~
          - do improvements?
      - ~~shift identical postcard Pixmap creation code from instances of
        CaptureIO.savePizza() to Capture.postcardPixmap()~~

  + ~~modify screen shot class to grab just the pizza base and toppings~~
      - ~~use this to create a pixmap to be passed to ServeBossScreen and 
        ServeWorkersScreen~~
          - ~~capturePizza() create pizza pixmap~~
      - ~~**question:** why is pizza too small in the PNG I produce?
        **answer:** because I hadn't set up a camera for the frame buffer~~
      - ~~update AndroidScreenshot and HtmlScreenshot~~
          - ~~**NB** the capturePizza() method should always return a Pixmap,
            and never null, as a pixmap will be disposed in 
            PizzaScreen.capturePizza()~~
      - ~~make a background to put behind this pizza for saving screen shot~~
          - ~~saveCapture() adds pizza texture to a background creates PNG~~

  + ~~don't add toppings when on Topping Menu~~
  
  + ~~add a message after first topping is selected telling player what to do~~
      
  + ~~add a 'back' button to the topping selection screen~~

  + ~~drop base/sauce/cheese logic restrictions~~
      - ~~let's just have fun it's a game not an accurate simulation!~~
      - ~~I am serious about fun, though~~
      - ~~please consider this submission for cool serious pizza game sim~~
      - ~~okay I know we said fun but let's not allow cheese on cheese or 
        sauce on sauce because I have no way of showing that~~
      
  + ~~get WebGL working~~
      - ~~it didn't seem to like the async loading in 
        Assets.loadCookScreenAssets() yesterday but now today it's fine?~~

  + ~~create platform-specific UiFont to provide BitmapFont for webgl, 
    FreetypeFont for desktop and android~~
    <https://github.com/libgdx/libgdx/wiki/Interfacing-with-platform-specific-code>
      - ~~create UiFont interface~~
      - ~~create UiSkin() constructor which takes a UiFont argument~~
      - ~~UiSkin calls uiFont.getUiFont()~~
      - ~~implement DesktopUiFont~~
      - ~~implement AndroidUiFont~~
      - ~~implement HtmlUiFont~~
  
  + ~~get WebGL working~~
      - ~~does WebGL work now: **YES** thanks to loading UI font via UiFont~~

  + ~~**BUG**: pizzaUi buttons get larger when pressed~~
      - ~~is the header image too tall, forcing these to shrink? yes~~
      - ~~set Pizza UI header size with UiSize.getImageWidth() and
        UiSize.getImageHeight()~~
  
  + ~~**BUG**: fix crash when touching pizza screen with no topping selected~~
      - ~~only update selected topping in PizzaScreen.touchDragged() when
        we have a selected topping~~
  
  + ~~**DECIDED TO HAVE NO OFFSET**: rethink touchscreen offset: it pretty 
    much always jumps~~

  + ~~complete the Pizza menu~~
      - ~~"let's make pizza" header~~
      - ~~undo button: pops last topping off stack or steps back through base 
        logic~~
      - ~~photo button~~
      - ~~COOK button~~
      
  + ~~implement Android back button~~
      - ~~go back from topping select menu~~
      - ~~quit app from pizza main menu~~
      - ~~quit app from CookScreen~~
      - ~~get this working better on Android~~
  
  + ~~add a second camera and viewport for ui scaled to screen/window~~
      - ~~UiSkin.class generates font right size for screen height~~
      - ~~create UiSize.class to return UI element sizes from static methods~~
      - ~~decouple Desktop window size from APP_WIDTH/HEIGHT constants~~
      - ~~two cameras in PizzaScreen.class: uiCamera and gameCamera~~
      - ~~two viewports in PizzaScreen.class: uiViewport and gameViewport~~
      - ~~gameViewport same as current viewport~~
      - ~~uiViewport same aspect ration, but fill screen pixels~~
      - ~~PizzaUi and MessageUi constructors have screenWidth, screenHeight 
        arguments. These are stored as fields and used to set widget sizes~~
      - ~~forget about handling font changes on screen resize for now~~
      - ~~resize camera and undo icons according to screen size~~
  
  + ~~add a second camera and viewport for ui scaled to screen/window~~
      - ~~UiSkin.class generates font right size for screen height~~
      - ~~create UiSize.class to return UI element sizes from static methods~~
      - ~~decouple Desktop window size from APP_WIDTH/HEIGHT constants~~
      - ~~two cameras in PizzaScreen.class: uiCamera and gameCamera~~
      - ~~two viewports in PizzaScreen.class: uiViewport and gameViewport~~
      - ~~gameViewport same as current viewport~~
      - ~~uiViewport same aspect ration, but fill screen pixels~~
      - ~~PizzaUi and MessageUi constructors have screenWidth, screenHeight 
        arguments. These are stored as fields and used to set widget sizes~~
      - ~~forget about handling font changes on screen resize for now~~
    
  + ~~implement asset assets~~
      - ~~dispose of assets~~
      - ~~check on Android~~
      - ~~check on WebGL~~
      - create asset management class to load and dispose screen assets:
        ~~Assets.class~~
  
  + ~~combine gameViewport and stageViewport into single viewport~~
      
  + ~~add Android touch offset~~
      - ~~when touching and dragging the selected topping should appear above
        finger position so it's actually visible~~

  + ~~**ABANDONED BECAUSE I DON'T GROK WINDOW CLASS** warning messages 
    appear in Window.class~~
      
  + ~~**THINGS MOSTLY THE SAME BUT TAKING UP MORE OF THE SCREEN AND A FONT 
    CHANGE** rethink screen size and aspect ratios to make things look nice 
    on my phone~~
      - ~~resize base toppings and change layout to have 20px margins all round.
        base is 560x560, menu fills space~~
      - ~~**VIEWPORT SIZE ISSUES MAKE THIS SOMETHING I WON'T PURSUE FOR NOW** 
        use freetype font class to resize UI fonts~~
        <https://github.com/libgdx/libgdx/wiki/Gdx-freetype>
      - ~~"Select topping:" on topping select UI now obscured by base. try
        white text with drop shadow~~
          - ~~make icons white with shadow also~~
  
  + ~~implement base logic~~
      - ~~create MessagePanel.class with showMessage(s) and clearMessage()
        methods~~
      - ~~add MessagePanel object, showMessage(s) and clearMessage() methods,
        to PizzaScreen.class~~
      - ~~in Pizza.addTopping():~~
          - ~~only add SAUCE if top topping is BASE~~
          - ~~only add CHEESE if top topping is SAUCE~~
          - ~~call PizzaScreen.showMessage(pizzamenuWarning) if these are 
            violated~~
      
  + ~~implement remove topping/undo~~
      - ~~ToppingMenu calls pizzaScreen.undoLastTopping()~~
      - ~~PizzaScreen calls pizza.undoLastTopping()~~
          - ~~Pizza tracks topping order in toppingOrder~~

  + ~~work out why base image shows up wrong size on Android and fix~~
  + ~~create Pizza class to display pizza~~
  + ~~create Base class~~
  + ~~make Android icon/app name look better~~
  + ~~drop Base class, make part of Pizza~~
  + ~~create setBaseTopping() method~~
  + ~~call setBaseTopping() on touch~~
  
  + ~~in PizzaScreen:~~
      - ~~create Topping selectedTopping field~~
      - ~~create setSelectedTopping method~~
      - ~~call the Pizza.addTopping method when we click somewhere~~
      - ~~in touchDown method call selectedTopping.copy method to get
        a Topping to pass to pizza.addTopping~~
      - ~~create toggleSelectedTopping(toppingName) which sets selectedTopping
        to toppingName IF it is not already selected, NONE otherwise~~
  
  + ~~in Pizza~~
      - ~~a toppingArray field to hold Toppings~~
      - ~~initialise topplingArray with a base topping~~
      - ~~create addTopping method which takes Topping object and adds it to
        toppingArray~~        
      - ~~call PizzaScreen.toggleSelectedTopping method when a menu item is 
        checked~~
      - ~~create Topping noneTopping to handle NONE selectedTopping case~~
        
  + ~~in ToppingMenu~~
      - ~~create addMenuItem to add a new TextButton item to table and to
        the buttonGroup~~
      - add eight menu items to menuItemList on construction
      
  + ~~create Topping.class:~~snasnasn
      - ~~float x, y, rotation~~
      - ~~Constants.ToppingName toppingName~~
      - ~~Sprite sprite~~
      - ~~draw method~~
      
  + ~~Topping.class:~~
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
  

  
## creating Topping and ToppingMenuButton objects

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
