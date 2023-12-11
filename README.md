Read REAMDE.md of model, view and controller

Brainstorming
Shapes:
- ShapeLoader? Dynamic loading of shapes (also implements dynamic linkage pattern)
- ShapePool? Implement object pool pattern so that shapes are reused (when a plate exits the screen, its added back to the pool and reused)
- ShapeFactory - Has a shape loader and has getShape() which returns a random shape from the pool
- Clown: - How to differentiate between his two arms/two stacks of plates


First Steps:
- Main menu, user clicks new game, a new game starts (whats the logic for new game starting?)
- Find images of plates, pots, etc and figure out how to efficiently load as they asked
- Render the clown at the bottom of the screen and control the clown with keyboard
- Generate random shapes and move them (either across the screen or falling down)