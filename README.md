# Cracking the Coding Interview Practice

My attempts at learning algorithms and data structures by attempting to solve the problems given in the sixth edition of
the book _Cracking the Coding Interview: 189 Programming Questions and Solutions_.

## Installation

- Clone the repo using one of the following steps:
    - `git clone git@github.com:neelkamath/ctci-practice.git`
    - `git clone https://github.com/neelkamath/ctci-practice.git`
- Install [OpenJDK 16 (HotSpot)](https://adoptopenjdk.net/?variant=openjdk16&jvmVariant=hotspot).
- Install the [Kotlin compiler](https://kotlinlang.org/docs/command-line.html).

## Usage

1. Go to the program's directory (e.g., `cd src/chapter1/problem1`).
2. Run (e.g., `kotlinc -script solution1.kts`).

## Contributing

Each program file is self-contained (i.e., doesn't reference other files). Top-level files in chapters such as [`src/chapter2/linkedList.kts`](src/chapter2/linkedList.kts) are meant for learning the ins and outs of specific data structures, and aren't meant to be run or used anywhere.

## License

This project is under the [MIT License](LICENSE).
