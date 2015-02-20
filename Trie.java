package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is my Trie structure.
 * @author omadarik
 */
public class Trie {
  private TrieNode root;

  /** 
   * This is the constructor for my Trie class
   */
  public Trie() {
    root = new TrieNode();
  }

  /**
   * Adds the word to the Trie
   * @param word
   */
  public void addToTrie(String word) {
  	root.addWord(word);
  }

  /**
   * Retrieve the words associated with the given prefix
   * @param prefix
   */
  public List<String> allWords(String prefix) {
  	TrieNode nodeAtLast = root;

  	//searches for the node representing the last character of the prefix
  	for (int i=0; i<prefix.length(); i++) {
  		nodeAtLast = nodeAtLast.getNode(prefix.charAt(i));
  		// if any of the prefix characters is not in the tree, then no words
  		// can be found for the prefix
  		if (nodeAtLast == null) {
  			return new ArrayList<String>();
  		}
  	}

  	//return the words found from the node for the last char
  	if(nodeAtLast.getWords() == null) {
  		return new ArrayList<String>();
  	} else {
  		return nodeAtLast.getWords();
  	}
  }
}