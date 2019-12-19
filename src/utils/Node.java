package utils;

import java.util.HashMap;
import java.util.Iterator;

public class Node
{
    private String value;
    private HashMap<String, Node> children;

    public Node(String value)
    {
        this.value = value;
        this.children = new HashMap<>();
    }

    public boolean contains(String value)
    {
        if (children.containsKey(value))
        {
            return true;
        }

        if (children.isEmpty())
        {
            return false;
        }

        for (String valueCheck : children.keySet())
        {
            boolean result = children.get(valueCheck).contains(value);

            if (result)
            {
                return true;
            }
        }

        return false;
    }

    public Node add(String parent, String child)
    {
        if (!this.contains(parent))
        {
            System.out.println("Adding " + parent + " to hashmap");
            children.put(parent, new Node(child));
        }

        Iterator<Node> iter = children.values().iterator();

        while (iter.hasNext())
        {
            String currentValue = iter.next().value;

            if (currentValue.equals(parent))
            {

            }
        }

        return this;
    }

    public void printTree()
    {
        for (Node node : children.values())
        {
            System.out.println(node.value);

            if (!children.isEmpty())
            {
                node.printTree();
            }
        }
    }
}
