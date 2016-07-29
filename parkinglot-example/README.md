### Attempt 2 ###

This branch represents a second attempt at a BDD approach based on the
following feedback.

1. behaviors should represent stuff the user cares about. the user doesnt care
   about whether the app accepts input as a file or as text. so that should
   not be part of the features list. 

2. features should represent and explain the behavior of the app, **in a way
   that is clear to business users as well**.

3. where applicable, ask yourself if changing the given or when will affect
   the outcome. keep it as generic as possible.
   
4. for each feature, follow the loop of adding failing ATDDs.
