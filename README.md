# Customizing Coffee Orders - Decorator Pattern Implementation

## Overview

A flexible ordering system for "Brewtiful" coffee shop that enables customers to create custom coffee beverages by combining a base coffee with various toppings and flavor shots. The system uses the **Decorator Pattern** to dynamically add features while maintaining clean, extensible code.

## Key Features

### ☕ Base Coffee Selection
- Start with a core coffee type (Espresso, Black Coffee, etc.)
- Each base coffee has a defined cost and description

### 🎨 Dynamic Customization
- **Flexible Additions**: Add toppings and flavor shots on top of any base coffee
- **Unlimited Combinations**: Mix and match any additions in any order
- **Real-Time Calculation**: Cost and ingredients update instantly as selections are made

### 🔄 Consistent Interface
- All components (base coffee and additions) share a common interface
- Decorators can wrap other decorators, enabling layered customization
- Seamless integration across the entire system

## System Architecture

### Design Pattern
This system implements the **Decorator Pattern** to:
- Add new features to objects dynamically without modifying existing code
- Maintain a single, consistent interface across all coffee components
- Support unlimited combinations of additions

## Component Structure

### 1. Component Interface
**Class**: `Coffee`

Defines the common operations for all coffee components:
```
+ getDescription(): String    // Returns ingredients list
+ getCost(): BigDecimal      // Returns total price
```

### 2. Concrete Component
**Class**: `BlackCoffee` (implements Coffee)

The base, undecorated coffee:
| Method | Returns |
|--------|---------|
| `getDescription()` | "Black Coffee" |
| `getCost()` | ₱100.00 |

### 3. Decorator Abstraction
**Abstract Class**: `CoffeeDecorator` (implements Coffee)

Ensures all decorators:
- Implement the Coffee interface
- Hold a reference to a wrapped Coffee object
- Can delegate to the wrapped component
- Can add their own enhancements

**Key Implementation**:
```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee wrappedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.wrappedCoffee = coffee;
    }
}
```

### 4. Concrete Decorators

#### Milk Decorator
**Class**: `Milk` (extends CoffeeDecorator)

| Method | Returns |
|--------|---------|
| `getDescription()` | `wrappedCoffee.getDescription() + ", Milk"` |
| `getCost()` | `wrappedCoffee.getCost() + ₱25.00` |

#### Caramel Syrup Decorator
**Class**: `CaramelSyrup` (extends CoffeeDecorator)

| Method | Returns |
|--------|---------|
| `getDescription()` | `wrappedCoffee.getDescription() + ", Caramel Syrup"` |
| `getCost()` | `wrappedCoffee.getCost() + ₱40.00` |

#### Additional Decorators
**Class**: `WhippedCream` (extends CoffeeDecorator)

| Method | Returns |
|--------|---------|
| `getDescription()` | `wrappedCoffee.getDescription() + ", Whipped Cream"` |
| `getCost()` | `wrappedCoffee.getCost() + ₱20.00` |

**Class**: `Espresso` (extends CoffeeDecorator)

| Method | Returns |
|--------|---------|
| `getDescription()` | `wrappedCoffee.getDescription() + ", Extra Espresso Shot"` |
| `getCost()` | `wrappedCoffee.getCost() + ₱35.00` |

## Usage Examples

### Example 1: Black Coffee with Milk
```java
Coffee order = new BlackCoffee();              // ₱100.00
order = new Milk(order);                       // +₱25.00
// Result: "Black Coffee, Milk" - ₱125.00
```

### Example 2: Complex Customization
```java
Coffee order = new BlackCoffee();              // ₱100.00
order = new Milk(order);                       // +₱25.00
order = new CaramelSyrup(order);              // +₱40.00
order = new WhippedCream(order);              // +₱20.00
// Result: "Black Coffee, Milk, Caramel Syrup, Whipped Cream" - ₱185.00
```

### Example 3: Decorator Stacking
```java
Coffee order = new BlackCoffee();
order = new Espresso(order);                   // Extra shot
order = new Milk(order);
order = new Milk(order);                       // Double milk
order = new CaramelSyrup(order);
// Result: Full customization with multiple of same addition
```

## Benefits

✅ **Open/Closed Principle**: Add new decorators without modifying existing classes  
✅ **Flexibility**: Combine any number of additions in any order  
✅ **Single Responsibility**: Each decorator handles one specific enhancement  
✅ **Reusability**: Decorators work with any coffee base  
✅ **Easy Maintenance**: Changes to one decorator don't affect others  
✅ **Scalability**: Simple to add new toppings/flavors  

## Class Diagram

[Insert Decorator Pattern UML Diagram here]

```
        ┌──��──────────┐
        │   Coffee    │ (Interface)
        ├─────────────┤
        │+ getDescription()
        │+ getCost()
        └─────────────┘
             ▲
             │
        ┌────┴────┐
        │          │
    ┌───────────┐ ┌──────────────────┐
    │BlackCoffee│ │CoffeeDecorator   │
    └───────────┘ ├──────────────────┤
                  │# wrappedCoffee   │
                  └──────────────────┘
                           ▲
              ┌────┬────┬──┴──┬────────┐
              │    │    │     │        │
           ┌──┴──┐ │    │     │        │
           │Milk │ │    │     │        │
           └─────┘ │    │     │        │
          ┌────────┴┐   │     │        │
          │CaramelSyrup│     │        │
          └──────────┘  │     │        │
        ┌───────────────┴┐    │        │
        │WhippedCream    │    │        │
        └────────────────┘    │        │
      ┌────────────────────┐   │        │
      │Extra Espresso Shot │   │        │
      └────────────────────┘   │        │
                            (More decorators...)
```

## Technical Implementation

- **Pattern**: Decorator Pattern
- **Structure**: Component Interface → Concrete Component → Abstract Decorator → Concrete Decorators
- **Flexibility**: Supports unlimited decorator combinations
- **Performance**: Minimal overhead per addition

## Getting Started

### Prerequisites
- Java 8 or higher (or equivalent language implementation)
- Basic understanding of design patterns and OOP


### Building Your Order
1. Create a base coffee: `new BlackCoffee()`
2. Wrap with desired decorators: `new Milk(coffee)`, `new CaramelSyrup(coffee)`
3. Query the final order:
   - `order.getDescription()` - Full ingredient list
   - `order.getCost()` - Total price

## Extending the System

### Adding a New Topping
Create a new decorator class:
```java
public class Chocolate extends CoffeeDecorator {
    public Chocolate(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + ", Chocolate";
    }
    
    @Override
    public BigDecimal getCost() {
        return wrappedCoffee.getCost().add(new BigDecimal("30.00"));
    }
}
```


**Last Updated**: February 2026  
**Pattern**: Decorator Pattern  
**Shop Name**: Brewtiful Coffee Shop
