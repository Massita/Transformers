# Welcome to Transformers App!

# Configure

This tutorial will help you configure and build the Transformers application.

## Prepare your computer

To start building the Transformers application, first you need to configure your IDE.
1. Download and install **Android Studio**
2. Clone this **Repository**
3. Open this project with **Android Studio**
4. Configure your **Device** it can be a real one or an AVD connected via ADB
5. **Build and install** the project.

## App details

The application has 3 activities.
1 - The dashboard: This is where your app starts! It lists your created Transformers and has the buttons to
access the battle screen, edit or add a new Transformer.
1.1 - By clicking on the add button(FAB), you'll be redirect to the second activity.
1.2 - By clicking on the 'fight' button, you'll check the results of the transformer's war.
1.3 - By clicking on a Transformer, you'll be redirect to it's details and you will be able to edit or delete it.
2 - On the new transformer's screen, you will give your Transformer a name and edit the attributes.
3 - The edit screen is the same as the new Transformer's screen,
but you`ll have your data filled based on your selected transformer.
On this screen, you can click to delete a transformer.
4 - When clicking to battle, you can have 3 differents screens:
a) If you have Optimus Prime and a Predaking battling battling each other, or a mirror battle of these Transformers,
then all Transformers will be destroyed, with no winner.
b) If a team eliminates more Transformers of the other team, this team will win the war.
On this screen, you can check some details: Number of battles of this war, the members of the winning team
and the members of the defeated team that didn't battle.
c) If you have a tie, then the screen will show you that it's a draw and the number of battles.

Some assumptions made:
1. The transformer's list is not sorted, but the war will consider the both list sorted.
2. The errors messages, handling and loading screens are not optimized in this version.
3. The unit test created has an example of mocking and verifying calls. It's testing only a Presenter
on the MVP architecture.
