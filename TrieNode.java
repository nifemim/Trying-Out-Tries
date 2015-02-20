package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class is my TrieNode structure.
 */
public class TrieNode {
  private TrieNode parent;
  private boolean isLeaf;
  private boolean isWord;
  private char key;
  private Map<Character,TrieNode> children;

  /**
   * Constructor for root node
   */
  public TrieNode() {
    children = new HashMap<Character,TrieNode>();
    isLeaf = true;
    isWord = false;
  }

  /**
   * Constructor for child node
   */
  public TrieNode(char key) {
    this();
    this.key = key;
  }

  /**
   * This method adds a word to the trie as the children of the current node
   */
  public void addWord(String word) {
    isLeaf = false;
    char current = word.charAt(0);
    
    if (!children.containsKey(current)) {
      children.put(current, new TrieNode(current));
      TrieNode newChild = children.get(current);
      newChild.parent = this;
    }

    if (word.length() > 1) {
      children.get(current).addWord(word.substring(1));
    } else {
      children.get(current).isWord = true;
    }
  }

  /**
   * Retrieves the node with the specified character as its key.
   */
  public TrieNode getNode(char key) {
    return children.get(key);
  }

  /**
   * Retrieves all the words associated with the children of this node.
   */
  public List<String> getWords() {
    List<String> allWords = new ArrayList<String>();
    
    if (isWord){
      allWords.add(makeString());
    }

    // Getting a set of entries
    Set<Entry<Character, TrieNode>> entrySet = children.entrySet();
    // Getting an iterator for the set of keys
    Iterator<Entry<Character, TrieNode>> i = entrySet.iterator();
    // Recursively adding any words that belong to any children
    if (!isLeaf) {
      while(i.hasNext()) {
        Map.Entry<Character, TrieNode> curr = (Map.Entry<Character, TrieNode>)i.next();
        if (curr.getValue() != null) {
          allWords.addAll(curr.getValue().getWords());
        }
      }
    }

    return allWords;
  }

  /**
   * The method below constructs a string upwards from the character of the
   * current node.
   */
  private String makeString() {
    if (parent == null) {
      return "";
    } else {
      char[] currentChar = {key};
      return parent.makeString()+new String(currentChar);
    }
  }
}