package com.ayago.datastructures;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<E> implements Iterable<E>{
    
    private int length;
    private int capacity;
    private E[] currentInternalArray;
    
    public DynamicArray(){
        length = 0;
        capacity = 0;
        currentInternalArray = (E[]) new Object[0];
    }
    
    @Override
    public Iterator<E> iterator(){
        return null;
    }
    
    public int size(){
        return length;
    }
    
    public boolean isEmpty(){
        return size() == 0;
    }
    
    public void append(E element){
        if(isEmpty()){
            currentInternalArray = (E[]) new Object[]{element};
            length = 1;
            capacity = 1;
        } else {
            if(capacity <= length + 1){
                doubleTheSize();
            }
            
            currentInternalArray[length] = element;
            length++;
        }
    }
    
    @SuppressWarnings("ManualArrayCopy")
    private void doubleTheSize(){
        E[] newArray = (E[]) new Object[length * 2];
        for(int index = 0; index < size(); index++){
            newArray[index] = currentInternalArray[index];
        }
        currentInternalArray = newArray;
        capacity = length * 2;
    }
    
    public E accessAtIndex(int index){
        if(index >= capacity || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return currentInternalArray[index];
    }
    
    public boolean contains(E element){
        if(isEmpty()){
            return false;
        }
        for(int index = 0; index <= size(); index++){
            E elementAtIndex = currentInternalArray[index];
            if((element == null && elementAtIndex == null) || elementAtIndex.equals(element)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void insertAtIndex(int index, E element){
        if(index >= capacity || index < 0){
            throw new IndexOutOfBoundsException();
        }
        
        E[] tempArray = (E[]) new Object[++length];
        for(int i = 0, j = 0; i < length; i++, j++){
            if(i < index){
                tempArray[i] = currentInternalArray[j];
            } else if(i == index){
                tempArray[i] = element;
                j--;
            } else {
                tempArray[i] = currentInternalArray[j];
            }
        }
        currentInternalArray = tempArray;
        capacity = length;
    }
    
    
    public boolean remove(E element){
        boolean removalSuccessful = false;
        int effectiveCapacity = size();
        E[] tempArray = (E[]) new Object[effectiveCapacity];
        
        int tempArrIndex = -1;
        for(int i = 0; i < size(); i++){
            E elementAtIndex = currentInternalArray[i];
            if((element == null && elementAtIndex == null) || elementAtIndex.equals(element)){
                removalSuccessful = true;
            }  else {
                tempArrIndex++;
                tempArray[tempArrIndex] = elementAtIndex;
            }
        }
        currentInternalArray = tempArray;
        capacity = effectiveCapacity;
        length = tempArrIndex + 1;
        return removalSuccessful;
    }
}
