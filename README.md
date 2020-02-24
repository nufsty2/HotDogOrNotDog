# HotDogOrNotDog
This ground-breaking app will tell a user if they have taken a picture of a hot dog or a not dog.

## System Architecture 
UML diagram of the system architecture can be found [here](https://www.lucidchart.com/invitations/accept/5068a356-a908-4d02-bb30-bb6fea071775)


## Making the PyTorch models
First [install pytorch](https://pytorch.org/get-started/locally/) which is probably something like
```
pip install torch torchvision
```
Make sure you have python (might need python 3) on your path and run:
```
make
```

The training images are found in [`pytorch/train_set`](pytorch/train_set)