"""
Runs tests on the code, accessing it through ctypes.
"""

import ctypes

import pytest

lib = ctypes.cdll.LoadLibrary("build/libCStructures.so")

def test_queues():
    queue = lib.newQueue(5)

    # Perform the following changes, periodically checking that what comes off is correct
    assert lib.isQueueEmpty(queue), "Queue should be empty"
    assert not lib.isQueueFull(queue), "Queue should not be full"

    lib.enqueue(queue, 5)
    # 5 _ _ _ _
    lib.enqueue(queue, 6)
    # 5 6 _ _ _
    assert lib.dequeue(queue) == 5, "Item dequeued should be 5"
    # _ 6 _ _ _
    lib.enqueue(queue, 1)
    # _ 6 1 _ _
    lib.enqueue(queue, 2)
    # _ 6 1 2 _
    lib.enqueue(queue, 3)
    # _ 6 1 2 3
    assert lib.dequeue(queue) == 6, "Item dequeued should be 6"
    # _ _ 1 2 3
    lib.enqueue(queue, 4)
    # 4 _ 1 2 3
    lib.enqueue(queue, 5)
    # 4 5 1 2 3
    assert lib.isQueueFull(queue), "Queue should be full"
    assert not lib.isQueueEmpty(queue), "Queue should not be empty"
    assert lib.dequeue(queue) == 1, "Item dequeued should be 1"
    # 4 5 _ 2 3
    assert lib.dequeue(queue) == 2, "Item dequeued should be 2"
    # 4 5 _ _ 3
    assert lib.dequeue(queue) == 3, "Item dequeued should be 3"
    # 4 5 _ _ _
    assert lib.nextInQueue(queue) == 4, "Next item should be 4"
    assert lib.dequeue(queue) == 4, "Item dequeued should be 4"
    # _ 5 _ _ _
    assert lib.dequeue(queue) == 5, "Item dequeued should be 5"
    assert lib.isQueueEmpty(), "Queue should be empty"

def test_stacks():
    stack = lib.newStack(5)

    # _ _ _ _ _
    assert lib.isStackEmpty(stack), "Stack should be empty"
    assert not lib.isStackFull(stack), "Stack should not be full"
    lib.pushStack(stack, 1)
    # 1 _ _ _ _
    assert lib.stackTop(stack) == 1, "Top of stack should be 1"
    assert not lib.isStackEmpty(stack), "Stack should not be empty after checking top element"
    lib.pushStack(stack, 2)
    # 1 2 _ _ _
    lib.pushStack(stack, 3)
    # 1 2 3 _ _
    lib.pushStack(stack, 4)
    # 1 2 3 4 _
    lib.pushStack(stack, 5)
    # 1 2 3 4 5
    assert lib.isStackFull(stack), "Stack should be full"
    assert not lib.isStackEmpty(stack), "Stack should not be empty"
    assert lib.stackTop(stack) == 5, "Top of stack should be 5"
    assert lib.stackPop(stack) == 5, "Should have popped 5"
    # 1 2 3 4 _
    assert lib.stackPop(stack) == 4, "Should have popped 4"
    # 1 2 3 _ _
    assert lib.stackPop(stack) == 3, "Should have popped 3"
    # 1 2 _ _ _
    assert lib.stackPop(stack) == 2, "Should have popped 2"
    # 1 _ _ _ _
    assert lib.stackPop(stack) == 1, "Should have popped 1"
    # _ _ _ _ _
    assert lib.isStackEmpty(stack), "Stack should be empty"
