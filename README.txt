JP Morgan "Message Processing" test
Andrew Forrest, andrew@dysphoria.net
23 July 2018

There are 3 Java executables (classes with 'main' method):

    jpinterview.ex1.run.Demonstrate
        Fully automatic: generates random messages, processes them
        and logs the results to stdout.

    jpinterview.ex1.run.ProcessInput
        Takes input either on stdin, or from file given as first argument,
        parses & processes the messages, and logs the output, as per
        the specification.

    jpinterview.ex1.run.CreateMessages
        Randomly generates test messages and writes them to stdout.
        One example output run is included in the top-level file
        "test-input.txt". Can be used to generate input for ProcessInput.

Sample output:

    PROCESSED 10 MESSAGES
    SALES:
      apple: 1 sales, 4p total
      planet: 10 sales, 180p total
      spaniel: 13 sales, 347p total
      spanner: 4 sales, 16p total

    PROCESSED 20 MESSAGES
    SALES:
      apple: 17 sales, 321p total
      planet: 10 sales, 180p total
      spaniel: 21 sales, 176p total
      spanner: 16 sales, 800p total

    PROCESSED 30 MESSAGES
    SALES:
      apple: 29 sales, 1,231p total
      planet: 12 sales, 224p total
      spaniel: 29 sales, 860p total
      spanner: 30 sales, 1,206p total

    PROCESSED 40 MESSAGES
    SALES:
      apple: 43 sales, 2,268p total
      banana: 3 sales, 11p total
      planet: 24 sales, 672p total
      spaniel: 29 sales, 860p total
      spanner: 44 sales, 1,626p total

    PROCESSED 50 MESSAGES
    SALES:
      apple: 56 sales, 848p total
      banana: 16 sales, 142p total
      planet: 44 sales, 647p total
      spaniel: 29 sales, 70p total
      spanner: 45 sales, 1,633p total

    TERMINATED PROCESSING
    ADJUSTMENTS:
      apple:
        Multiply ⨉4
        Add 3p
        Add 2p
        Multiply ⨉5
        Add 5p
        Multiply ⨉4
        Add 2p
      banana:
        Multiply ⨉4
        Add 2p
        Subtract 2p
      planet:
        Add 2p
        Multiply ⨉2
        Multiply ⨉2
        Multiply ⨉3
        Add 3p
      spaniel:
        Add 1p
        Multiply ⨉4
        Add 1p
        Subtract 5p
      spanner:
        Multiply ⨉1
        Multiply ⨉5
