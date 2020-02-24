output-file = app/src/main/assets/model.pt
python-file = pytorch/android-export.py
model-file = pytorch/hotdog-model
model-creation-file = pytorch/finetunning-model.py
trainset-hotdog = pytorch/train_set/hotdog/*
trainset-nothotdog = pytorch/train_set/not_hotdog/*

android-model: $(output-file)

$(output-file): $(python-file) $(model-file)
	python $(python-file)

$(model-file): $(model-creation-file) $(trainset-hotdog) $(trainset-nothotdog)
	python $(model-creation-file)

clean:
	rm $(output-file) $(model-file)