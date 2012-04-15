# Clojure Smuggler

This is my answer to the problem located [Here] (http://spin.atomicobject.com/2011/05/31/use-clojure-to-move-drugs-a-programming-challenge/)

# Standalone Jar

The standalone jar is just that.  You can run this on your machine with
just this file.

```
java -jar smuggler-0.0.2-standalone.jar
```

# Setup / Usage
These directions are geared towards a mac, because thats what I use.

NOTE: These instructions are for if you use [homebrew](https://github.com/mxcl/homebrew).

```
> brew install leiningen
> git clone git@github.com:zenom/clojure-smuggler.git
> cd clojure-smuggler/
```

## Run the code within lein
```
> lein trampoline run
```

Here is some sample output:

```
How much can granny carry?: 10
How many dolls do you have?: 30
Street Value: 80
Total Weight: 10 for handbag size of 10


---debug----

Selected Dolls: (doll-2 doll-10 doll-11 doll-20 doll-22 doll-26)

Doll Data:
{:weight 2, :name doll-2, :value 12}
{:weight 2, :name doll-10, :value 14}
{:weight 2, :name doll-11, :value 5}
{:weight 1, :name doll-20, :value 19}
{:weight 2, :name doll-22, :value 18}
{:weight 1, :name doll-26, :value 12}
```

## Run the tests
```
> lein test
```

You should see something like the following:

```
Testing smuggler.test.core

Ran 7 tests containing 10 assertions.
0 failures, 0 errors.
```
