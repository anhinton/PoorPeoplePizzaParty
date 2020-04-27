# TODO
		
  + texture packing
      
  + **BUG**: fix transparent swirl (through to postcard background) when swirl 
    is last placed
      
  + do a Vector Android app icon
  
  + stop debugging for release
      - remove debugging statements Gdx.app.log
  
  + investigate desktop full screen and window resizing
      - going to need to make a UiSkin.resize() called from PoorPeoplePizzaParty() for font
        sizes
        
  + revise credits
  
  + consider a thicker font for credits to make more legible on mobile
  
## Done

  + ~~drop freetype font support~~

  + ~~add sound effect on changing sound volume in settings~~
    
  + ~~**WILL NOT IMPLEMENT** add Coco Wiggler 4 music which plays after first "loss"~~

  + ~~**DO NOT IMPLEMENT:** there doesn't seem to be much point to doing this, and I can't 
    work out how I would do it for any given Screen.~~
    ~~go to loading screen when resuming from pause on Android~~
    ~~<https://github.com/libgdx/libgdx/wiki/Managing-your-assets#resuming-with-a-loading-screen>~~
  
  + ~~camera flash effect on PostcardScreen~~
	    
  + ~~delay "fired" button on ServeBossScreen~~
   
  + ~~implement audio~~
      - ~~topping sound effects~~
      - ~~camera sound on PostcardScreen~~
      - ~~guitar pick scrape for boss entrance~~
	  - ~~add a Music and Sound volume controls in Settings~~
	      - ~~hold icons to mute/full~~
	  - ~~Music~~:
	      - ~~folk Funiculi/Club Penguin pizza on start~~
	      - ~~metal Funiculi on boss firing~~
	  - ~~don't mute system audio? or when you mute game audio it enables
	    system audio? **UPDATE**: this seems to be enabled without me doing anything~~
	  - ~~save audio volumes using Preferences~~

  ~~+ migrate assets to single load on launch with loading bar~~
      - ~~create Assets.loadGameAssets() which loads all graphics assets but does not block~~
      - ~~create TitleScreen.class~~
          - ~~display "Loading" and a slider showing progress~~
          - ~~call Assets.loadGameAssets() in constructor~~
          - ~~follow pattern on 
            [Managing your assets](https://github.com/libgdx/libgdx/wiki/Managing-your-assets)
            in section starting "So far we only queued assets to be loaded [...]" which 
            demonstrates how to show progress while loading asynchronously~~
      - ~~set first screen in PoorPeoplePizzaParty to TitleScreen~~
	  
  + ~~move viewport boilerplate to a utils Class~~
      - ~~created static methods UiSize.getViewportHeight() and UiSize.getViewportHeight()~~
      - ~~**SOLVED**: button width problems when viewport is taller than expected:
        no longer a problem as button sizes are now fixed in Constants instead of calculated 
        in UiSize~~
  
  + ~~add "do you want to quit?" dialog to TitleScreen~~
      - ~~new CurrentTitleMenu value QUIT~~
          - ~~goBack() from TITLE -> QUIT (unless on WebGl version)~~
          - ~~goBack() from QUIT -> quit()~~ 
      - ~~"Yes" button calls quit()~~
      - ~~"No" button sets menu back to TITLE~~
  
  + ~~give fonts meaningful names~~
      
  + ~~go back to a single viewport size for UI; do away with all these font sizes~~
      - ~~it turns out maintaining all of this UI size logic is a reall pain in the butt that
        I don't really need to bother about~~
        
  + ~~create TitleScreen class~~
      - ~~Poor People Pizza Party~~
      - ~~Play, Settings, Quit buttons~~
      - ~~Settings has audio and music volume sliders~~
      - ~~change Android back button behaviour in PizzaScreen.class to go back 
        to here~~
      - ~~touch Labels on either end of volume settings sliders to mute/full volume~~
      - ~~volume Image icons press to raise or lower volumes~~
      - ~~volume level /100 displayed to right~~
      - ~~no Quit button on Html/WebGL~~
      - ~~Credits button in Settings menu displays Credits ui~~
      - ~~implement goBack() to catch ESC presses~~
  
  + ~~make Title header somewhat cooler~~
      
  + ~~add "close" button to PizzaScreen which goes back to TitleScreen~~
          
  + ~~pop up a fake "score" when a topping is placed~~
      - ~~"+6" 2x then "+666"~~
      - ~~drifts upwards and fades~~
      - ~~centred on topping x, y~~
      - ~~**SOLVED** work out why font is so much bigger on Android than on Desktop:
        I was plotting Points font in pizza/sprite coordinates, but font sizes are in UI
        coordinates~~

  + ~~switch to fixed font sizes for screen heights >= 1080, >= 720, < 720~~
      - ~~delete DesktopUiFont, AndroidUiFont, HtmlUiFont, UiFont~~
      - ~~UiSkin() takes height argument: screen height~~
      - ~~UiFont loads 64pt, 43pt, or 36pt button and label fonts for above screen sizes~~
  
  + ~~change title desktop on save postcard to... dialog (currently says "serialize" =P)~~ 
          
  + ~~remove postcard debugging code~~

  + ~~save Pizza to local storage on exit, reload on start~~
      - ~~DO IT AGAIN BUT USE XML~~
      - ~~decide whether Preferences or a Local file is better for this, remembering
        that Preferences will be used for Music/Audio volume:~~
          - ~~DO NOT use Preferences as I am saving as XML and and XML string inside Preferences
            gets escaped out~~
      - ~~Pizza.serialize() returns XML of toppings list~~
      - ~~PizzaScreen.save() saves XML of toppings to storage~~
          - ~~calls Pizza.serialize() to get XML~~
          - ~~calls Pizza.saveFile() to get FileHandle~~
          - ~~Desktop saves to External storage~~
          - ~~Android saves to Local storage~~
          - ~~no save support for WebGl/html~~
      - ~~save() on exiting PizzaScreen~~
          - ~~PizzaScreen.pause()~~
          - ~~don't save on cook() or createPostcard(): the current way of passing Pizza
            objects can handle this~~
      - ~~create Pizza.load() to deserialize Pizza~~
          - ~~create Pizza.addTopping() case for BASE: setBaseTopping() but nothing else,
            otherwise BASE stacks as you save and load~~
      - ~~load() on init~~
          - ~~add loadAutosave argument to new Pizza() constructor. true when called from
            PoorPeoplePizzaParty, false from ServeBossScreen and ServerWorkersScreen~~
      - ~~implement for Desktop, then go back and do Android~~
      - ~~WebGl/html now failing to compile: I need to re-implement this using
        platform-specific code~~
          - ~~CaptureIO.savePizzaXml(String pizzaXml) called by PizzaScreen.save()~~
          - ~~String CaptureIO.loadPizzaXml() called by PizzaScreen.load()~~
              - ~~returns an empty String when load file doesn't exist~~
              - ~~Pizza.deserialize() does not attempt to read XML if rootElement is null,
                i.e. if empty string is passed in~~
          - ~~implement methods in DesktopCaptureIO~~
          - ~~implement methods in AndroidCaptureIO~~
          - ~~empty methods in HtmlCaptureIO~~
      - `~~test test test!~~
      - ~~remove save() debugging code~~
          - ~~hotkey~~
          - ~~print XML~~

  + ~~hold Undo button on PizzaScreen to remove all toppings~~
      - ~~**UNDID THIS: create UndoButton which implements GestureListener~~
      - ~~handle long press logic in PizzaScreen.render()~~
      - ~~implement Pizza.removeAllToppings()~~
      - ~~call Pizza.removeAllToppings() from PizzaScreen.removerAllToppings()~~
  
  + ~~create PostcardScreen class~~
      - ~~photo button takes you to this screen so you can see the postcard
	    before saving/sharing~~
	  - ~~back button/Esc goes back to PizzaScreen~~
	  - ~~Desktop: save and back UI buttons~~
	  - ~~Android: share and back UI buttons~~
	  - ~~create some more postcards for variety~~
	  - ~~work out why screen is resizing on Android~~
	  - ~~render pizza y-up~~
	  - ~~Html: back button~~
	  - ~~**IT'S FINE WHERE IT IS**: move Postcard.fileName() somewhere sensible~~
		
  + ~~bring back web version~~
      - ~~get web version to run~~
      - test web version actually works
          - ~~currently failing in Pizza.getPixmap() when doing BufferUtils.copy()~~
          - ~~CookScreen~~
          - ~~PizzaScreen~~
          - ~~PostcardScreen~~
          - ~~ServeBossScreen~~
          - ~~ServeWorkersScreen~~
      - ~~only the back button shows on the HTML postcard screen. use snipping tool, gamers!~~
    
  + ~~**BUG** work out why background from old postcard shows behind pizza when quickly going
  	back and forth from PostcardScreen~~
	  - ~~this seems to only happen when quickly creating a new Pizza pixmap after 
	    disposing of one. It's possible to recreate this when going back and forth
        from ServeBossScreen. Currently only happening on Linux laptop Desktop and
        Android emulator build. Not happening in WebGl build on laptop, or on desktop PC.
        I need to test on some other computers, maybe do a proper build.~~
  
  + ~~pick a better font~~
      - ~~Inconsolata for buttons and Podkova for labels~~
        
  + ~~create ServeWorkersScreen.class~~
      - ~~make image assets~~
          - ~~pizza party/worker party~~
      - ~~send here from CookScreen choice~~
      - ~~it's a pizza party celebrate!~~
	  - ~~FlyingPizza class~~
	      - ~~spawn at random location off-screen~~
		  - ~~fly across screen~~
		  - ~~dispose once off-screen~~
		  - ~~randomise sprite rotation: will require re-calculating 
		    when sprite leaves screen~~
	  - ~~spawn FlyingPizzas at random intervals~~
	  - ~~LunchPhoto class~~
	      - ~~show colour Sprite by default~~
		  - ~~function to switch to grey~~
	  - ~~party picture turns grey~~
	  - ~~blood drip effect scrolls down from top~~
	  - ~~Boss class~~
          - ~~boss pops up~~
		  - ~~says "I can't believe you people eat this mass-produced
		    rubbish"~~
      - ~~everyone fired~~
      - ~~implement Android back button logic~~
      - ~~fix UI scale for Android~~
	  - ~~make "everyone has been fired" bigger and in middle of screen~~
	  - ~~make grayscale version of LunchPhoto have inverted colours~~
	  - ~~PizzaPartyAnimation.class~~
	      - ~~flash "PIZZA" and "PARTY" handwritten text on screen while 
		    partying~~
	  - ~~click/touch to advance~~
	  
  + ~~implement Cook screen~~
      - ~~countdown timer~~
      - ~~then show a cooked pizza and give option to serve to Bosses or 
        Workers~~
      - ~~take Pizza from PizzaScreen as argument~~
      - ~~implement Android back button logic~~
          - ~~go back to PizzaScreen with existing Pizza~~
      - implement CookScreen.serve()
          - ~~send to ServeBossScreen for BOSS~~
          - ~~send to ServerWorkersScreen.class for WORKERS~~

  + ~~create ServeBossScreen class~~
      - ~~make Boss sprite~~
      - ~~send here from CookScreen choice~~
      - ~~boss says either "who eats this rubbish" or "is this game about me?"~~
      - ~~"you have been fired..." button. pressing this starts a new pizza~~
      - ~~implement Android back button logic: go back to serve choice~~
      - ~~fix aspect ratio of Pizza on ServeBossUi~~
        
  + ~~implement DesktopCaptureIO~~
      - ~~split Screenshot into Capture class and CaptureIO interface~~
      - ~~generate sensible timestamped filename~~
          - ~~Capture.fileName() returns filename~~
          - ~~CaptureIO instances use Capture.fileName() in savePizza() to
            save to sensible location~~
      - ~~implement choose file location dialog~~
            
  + ~~implement AndroidDesktopCaptureIO~~
          - ~~save PNG file to `{Environment.DIRECTORY_PICTURES}/PoorPeople` 
            directory~~
          - ~~**NB:** needed to drop Android target SDK back to 20 (Android 4.4) 
            to avoid new Android permission model introduced in Android 6.0~~
      - ~~shift identical postcard Pixmap creation code from instances of
        CaptureIO.savePizza() to Capture.postcardPixmap()~~
      - ~~replace PNG saving in AndroidCaptureIO with Android Sharing Files~~
        <https://developer.android.com/training/secure-file-sharing/>
        
  + ~~implement for HtmlDesktopCaptureIO NO LONGER DOING AN HTML BUILD~~
      - ~~remove fancy timestamp code using SimpleDateFormat from
        Capture.fileName() as this does not seem to be available to Html.
        filename now uses TimeUtils.millis()~~

  + ~~target Android SDK 28~~
      - ~~fix screenshot permissions (oh no!)~~
      
  + ~~create a screen shot interface~~
      - ~~create platform-specific classes as described at~~
        <https://github.com/libgdx/libgdx/wiki/Interfacing-with-platform-specific-code>

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

~~The plan is to have eight toppings available from a UI menu which can
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
object to the array of Topping objects held in Pizza.~~
