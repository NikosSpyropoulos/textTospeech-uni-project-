# textTospeech-uni-project-

# Purpose

The purpose of this project is to develop an editor that allows preparing text documents and
transforming them to audio using external text to speech libraries. ( In the files there is a demoVideo that includes all the functionalities of the app)

### In the project are used the following architecture and patterns 

# MVC Architecture

Using this architecture we seperate the application in three parts. Models, Views, Controllers 
#### Models: Document, Line
 classes that are responsible for the representation and
the management of the documents. 

#### Views: MainMenu, NewDocumentView, DocumentEditorView
 classes that are responsible for the visualization of the
documents and the interaction with the user. 

#### Controllers: Command implementation (listeners)
 classes that control the data flow between the model and the view elements. In other words,
these classes realize the reactions of the application to the user input. 

# Meditarator pattern(MANAGERS): Document Manager, Replay Manager
 Managers are responsible for the various business logic units such as Document related actions
with Document Manager, or for Replay Command related actions with Replay Manager.

# Command Pattern:
 the different commands that are supported by the Text2SpeechEditor are classes that
implement the Java Swing ActionListener interface. In particular, we assume commands that
correspond to the different user stories that should be supported by the editor (NewCommand,
EditCommand, SaveCommand, DocumentToSpeech, etc.). These classes implement the commands
using information that is provided by the GUI classes of the view package and the application logic classes of the model package. In particular, the
command classes use the current document object that is manipulated by the user, which belongs to
the Document class. The command objects are added as action listeners to the application control
widgets (buttons, menu items, ...). Based in the Command pattern, we can easily configure the Text2SpeechEditor with an extensible set of
commands. To support new commands we just have to develop new classes that implement the
general ActionListener interface. Moreover, the pattern allows storing in a list of commands (held by an
object of the ReplayManager class) that have been performed by the user to facilitate replaying the
sequence multiple times (ReplayCommand). 
# Parameterized Factory pattern: Command Factory, Strategy Factory, TextToSpeechApi Factory
#### CommandsFactory
is responsible for the creation of objects that implement the ActionListener interface. Based on 
this pattern, we can easily extend the application logic with new classes that
implement the ActionListener interface, without having to change the existing logic too much see OCP
principle - open for extension closed to modification). In particular, to deal with the creation of the
objects of a new class we just have to modify the code of the factory method and nothing else. 
#### StrategyFactory
is responsible for the creation of objects that implement the EncodingStrategy
interface. Based on this pattern, we can easily extend the application logic with new classes that
implement the EncodingStrategy interface, without having to change the existing logic too much see
OCP principle - open for extension closed to modification). In particular, to deal with the creation of the
objects of a new class we just have to modify the code of the factory method and nothing else. 
####  TextToSpeechApiFactory
is responsible for the creation of objects that implement the TextToSpeechAPI
interface. Based on this pattern, we can easily extend the application logic with new classes that
implement the TextToSpeechAPI interface, without having to change the existing logic too much see
OCP principle - open for extension closed to modification). In particular, to deal with the creation of the
objects of a new class we just have to modify the code of the factory method and nothing else. 

# Strategy & Template Method Pattern:
 Using the above patterns we have achieved the clear definition for high level functions in a required
functionality, eg Encode. Thus, implementing a generic interface, this is a commitment to the other methods that
will implement that a certain number of functions or certain actions should
implemented. Then, the respective implementations of the main interface may contain abstract methods. The classes
with abstract methods can be extended by other classes that will make the definition even clearer
for the various versions and implementations of an even more specific implementation

# Adapter Pattern
 we define a general TextToSpeechAPI interface. Document manager
 class has as attributes references to the TextToSpeechAPI which are initialized to objects that
implement the interface, via parameters of the classes' constructors. The TextToSpeechAPI interface has
at least 2 implementation. The FreeTTSAdapter implementation realizes the interface using the Free TTS
API2. On the other hand, the FakeTextToSpeechAPI is a fake adaptor that we can use to test our
application in isolation from the actual text to speech API that is used. Instead of transforming text to
speech the fake adaptor just provides methods that we shall use to write assertions for our tests in the
JUnit framework

### At the end I would like to add that in this project maven is used to save the jar files of the library FreeTTS  that we use
