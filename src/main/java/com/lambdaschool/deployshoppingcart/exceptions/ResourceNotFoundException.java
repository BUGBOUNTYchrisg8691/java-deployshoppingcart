package com.lambdaschool.deployshoppingcart.exceptions;

/**
 * A custom exception to be used when a resource is not but is suppose to be
 */
public class ResourceNotFoundException
    extends RuntimeException
{
    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message)
    {
        super("Error from a Lambda School Application " + message);
    }
}