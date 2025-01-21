package com.sample.microservices.common.versions.v17;

public class SealedClasses {
    public static void main(String[] args) {
        String obj = "Welcome Sealed Classes!";
    }
}

/*

    Sealed classes allow you to restrict which classes or interfaces can extend or implement them.
    This feature enhances encapsulation and helps maintain code integrity by controlling
    who can inherit from a sealed class.

    Any other class that tries to extend the Shape class(apart from permit classes like Circle,
    Square, and Triangle)will result in a compilation error

    Sealed classes restrict which other classes may extend them. For a Class to be sealed
    you need to define which all classes are allowed to extend that class.

    Permitted subclasses are defined using Permit clause and only this classes can extend the sealed class.

    The subclasses of a sealed class can be either final, sealed, or non-sealed:

    Final: Cannot be subclassed further.
    Sealed: Can be subclassed but must also specify its permitted subclasses.
    Non-sealed: Can be subclassed by any class (essentially opting out of the sealing contract).

    Interfaces: Sealed classes can also be interfaces. The same rules apply: you must explicitly specify
    which interfaces or classes are permitted to extend or implement the sealed interface.

*/

sealed class Shape permits Circle, Square, Triangle, Rectangle {
    // Common properties and methods for all shapes
}

final class Circle extends Shape {
    // Circle-specific properties and methods
    public void draw() { /* draw a Circle */ }
}

final class Square extends Shape {
    // Square-specific properties and methods
}

final class Triangle extends Shape {
    // Square-specific properties and methods
}

non-sealed class Rectangle extends Shape {
    // Triangle-specific properties and methods
}


/*
compile error

final class OtherShape extends Shape {
    // Triangle-specific properties and methods
}
*/