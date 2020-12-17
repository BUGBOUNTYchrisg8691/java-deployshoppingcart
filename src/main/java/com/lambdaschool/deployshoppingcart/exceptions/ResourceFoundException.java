package com.lambdaschool.deployshoppingcart.exceptions;

/**
 * A custom exception to be used when a resource is found but is not suppose to be
 */
public class ResourceFoundException
    extends RuntimeException
{
    /**
     * Instantiates a new Resource found exception.
     *
     * @param message the message
     */
    public ResourceFoundException(String message)
    {
        super("Error from a Lambda School Application " + message);
    }
}