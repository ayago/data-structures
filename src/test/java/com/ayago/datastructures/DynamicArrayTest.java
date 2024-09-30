package com.ayago.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicArrayTest{
    
    @Test
    void emptyArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        assertEquals(0, dynamicArray.size());
        assertTrue(dynamicArray.isEmpty());
    }
    
    @Test
    void appendElementArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("ONE");
        
        assertEquals(1, dynamicArray.size());
        assertFalse(dynamicArray.isEmpty());
        elementExpectedAtIndex("ONE", dynamicArray, 0);
    }
    
    @Test
    void appendMultipleElementsArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("ONE");
        dynamicArray.append("THREE");
        dynamicArray.append("TWO");
        
        assertEquals(3, dynamicArray.size());
        assertFalse(dynamicArray.isEmpty());
        elementExpectedAtIndex("ONE", dynamicArray, 0);
        elementExpectedAtIndex("THREE", dynamicArray, 1);
        elementExpectedAtIndex("TWO", dynamicArray, 2);
    }
    
    @Test
    void insertElementAtFirstPosition(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.insertAtIndex(0, "ONE"));
    }
    
    @Test
    void insertElementAtNegativeIndex(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.insertAtIndex(-1, "ONE"));
    }
    
    @Test
    void insertElementAtIndexGreaterThanCapacity(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.insertAtIndex(1, "ONE"));
    }
    
    @Test
    void insertElementAtFirstIndexOnNonEmptyArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        dynamicArray.insertAtIndex(0, "ONE");
        
        assertFalse(dynamicArray.isEmpty());
        assertEquals(2, dynamicArray.size());
        elementExpectedAtIndex("TWO", dynamicArray, 1);
        elementExpectedAtIndex("ONE", dynamicArray, 0);
    }
    
    @Test
    void insertElementAtMiddleOfArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        dynamicArray.append("THREE");
        dynamicArray.insertAtIndex(1, "ONE");
        
        assertFalse(dynamicArray.isEmpty());
        assertEquals(3, dynamicArray.size());
        elementExpectedAtIndex("TWO", dynamicArray, 0);
        elementExpectedAtIndex("ONE", dynamicArray, 1);
        elementExpectedAtIndex("THREE", dynamicArray, 2);
    }
    
    @Test
    void removeOnEmptyArray(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        boolean result = dynamicArray.remove("ONE");
        assertFalse(result);
    }
    
    @Test
    void removeNonExistingElement(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        boolean result = dynamicArray.remove("ONE");
        assertFalse(result);
    }
    
    @Test
    void removeElement(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("TWO");
        boolean result = dynamicArray.remove("TWO");
        assertTrue(result);
        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }
    
    @Test
    void removeElementAtTheMiddle(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("ONE");
        dynamicArray.append("TWO");
        dynamicArray.append("THREE");
        dynamicArray.append("FIVE");
        
        boolean result = dynamicArray.remove("THREE");
        
        assertTrue(result);
        assertFalse(dynamicArray.isEmpty());
        assertEquals(3, dynamicArray.size());
        elementExpectedAtIndex("ONE", dynamicArray, 0);
        elementExpectedAtIndex("TWO", dynamicArray, 1);
        elementExpectedAtIndex("FIVE", dynamicArray, 2);
    }
    
    @Test
    void removeElementAtEarlyMiddle(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("ONE");
        dynamicArray.append("TWO");
        dynamicArray.append("THREE");
        dynamicArray.append("FIVE");
        
        boolean result = dynamicArray.remove("TWO");
        
        assertTrue(result);
        assertFalse(dynamicArray.isEmpty());
        assertEquals(3, dynamicArray.size());
        elementExpectedAtIndex("ONE", dynamicArray, 0);
        elementExpectedAtIndex("THREE", dynamicArray, 1);
        elementExpectedAtIndex("FIVE", dynamicArray, 2);
    }
    
    @Test
    void removeElementAtTheEnd(){
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.append("ONE");
        dynamicArray.append("TWO");
        dynamicArray.append("THREE");
        dynamicArray.append("FIVE");
        
        boolean result = dynamicArray.remove("FIVE");
        
        assertTrue(result);
        assertFalse(dynamicArray.isEmpty());
        assertEquals(3, dynamicArray.size());
        elementExpectedAtIndex("ONE", dynamicArray, 0);
        elementExpectedAtIndex("TWO", dynamicArray, 1);
        elementExpectedAtIndex("THREE", dynamicArray, 2);
    }
    
    private static void elementExpectedAtIndex(
        String expectedElement, DynamicArray<String> dynamicArray, int elementIndex){
        assertEquals(expectedElement, dynamicArray.accessAtIndex(elementIndex));
        assertTrue(dynamicArray.contains(expectedElement));
    }
}
