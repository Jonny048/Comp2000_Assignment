COMP2000 Assignment: Grid Game with Inheritance, Interfaces, and Generics
For this assignment, I took our classwork grid from Week 5 and built on it to create a game. My main goal was to add some new features while making sure the code was well-organised using inheritance, interfaces, and generics.

Project Functionality
This project extends the base grid game by introducing new, interactive elements and a more varied environment:
- Actors: The primary characters of the game, including a Dog, Cat, and Bird. These characters are able to navigate the grid.
- Items: Passive game objects, such as a Bone, Fish, and Seeds, which can be found on the grid and collected.
- Diverse Cells: The game board now features different types of terrain, including Sky and Grass, which have distinct visual properties.
- Bot AI: The new MoveTowardsTheFood action provides a strategic movement behaviour for bot-controlled players, allowing them to intelligently seek out their preferred food items.
- Scoring System: Each actor now has a score which is incremented upon the successful collection of their designated food item.
- This new functionality allows specific actors to "consume" or interact with certain items, adding a new layer of gameplay and showcasing a robust, object-oriented design.

Technical Design and Rationale
The new functionality was designed with a strong focus on demonstrating inheritance, interfaces, and generics to create a clean, maintainable, and type-safe program.

Inheritance:
- Actors: The Actor class serves as an abstract base class, providing core functionality for all game characters. It defines shared attributes such as loc (location) and moves, as well as common methods like paint() and setLocation(). Subclasses such as Dog, Cat, and Bird inherit these features and implement their own specific behaviours, such as their unique visual shape (setPoly()) and their food preferences.
- Items: The Item class serves a similar purpose, acting as an abstract base for all collectible items. Subclasses (Bone, Fish, Seeds) inherit fundamental properties and provide their own unique visual representation (paint()).
- Cells: The Cell class is now an abstract base class, with new subclasses like Grass and Sky inheriting its core properties. This allows different cell types with unique behaviours and visual representations to be added to the game while still being handled uniformly by the core game logic.

This hierarchical structure prevents code duplication and facilitates the easy addition of new actors or items in the future simply by extending the base classes.

Interfaces:
- Giveable: The Giveable interface provides a clear contract for any Item that can be consumed by a specific actor. It defines a single method, canBeGivenTo(Actor actor), which determines if the item is compatible.
- Botmover: The Botmover interface was implemented by the new MoveTowardsTheFood class. This approach allows a Botmover to define a specific movement strategy, which can be assigned to any bot-controlled Actor. Using an interface enables us to introduce different bot behaviours (e.g., a "random mover" or a "fleeing mover") without needing to alter the Actor class itself, thereby demonstrating a flexible and decoupled design.
- Polymorphism: The Stage class can interact with any Giveable item without needing to know its specific type. For example, it can simply invoke item.canBeGivenTo(actor), and the correct implementation (e.g., a Bone checking for a Dog) will be executed automatically. This is an excellent use of polymorphism, which ensures the game's logic remains clean and easy to manage.

Generics:
- Type-Safe Actors: Generics were incorporated by making the Actor class itself generic (public abstract class Actor<T>). This design ensures that each subclass must specify the exact type of item it can consume.
- Enforced Type Safety: Subclasses such as Dog are declared as Dog extends Actor<Bone>, which enforces that the Dog class's eats method only accepts a Bone object. Similarly, Cat extends Actor<Fish> ensures that a Cat can only eat a Fish. This design leverages the Java compiler to guarantee type correctness, which prevents potential runtime errors and makes the code significantly more robust than relying on instanceof checks.

How To Compile:
Just open your Main.java file and click the Run button that appears in the top-right corner of the editor.  This button will automatically compile all the necessary Java files and then execute your program

How to Play:
The goal of the game is to move your actors around the grid to collect items.
- Move your Actors: Use your mouse to click on a human-controlled actor to select it. The cells where it can move will be highlighted. Then, click on one of the highlighted cells to move your actor to that spot.
- Identify Players and Bots: On the side panel, each actor is labeled as either (Human) or (Bot). You can only control the human actors.
- Collect Items: Each actor collects a specific item:
 - The Dog collects a Bone
 - The Cat collects a Fish
 - The Bird collects a Seed
