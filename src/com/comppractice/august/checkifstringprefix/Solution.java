package com.comppractice.august.checkifstringprefix;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isPrefixString("hellowhellworld", new String[] { "hello", "hell",
                "o","world", "hellfire" }));
    }

    public boolean isPrefixString(String s, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        return searchInTrie(s.toCharArray(), 0, root, root);
    }

    private boolean searchInTrie(char[] charArray, int index, TrieNode node, TrieNode root) {
        if (index < charArray.length) {
            if (node.childNodes[charArray[index] - 'a'] != null) {
                return searchInTrie(charArray, index + 1, node.childNodes[charArray[index] - 'a'], root) || (
                        node.childNodes[charArray[index] - 'a'].completeWord && searchInTrie(charArray, index + 1, root,
                                root));
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void insert(TrieNode root, String word) {
        for (char character : word.toCharArray()) {
            if (root.childNodes[character - 'a'] == null) {
                root.childNodes[character - 'a'] = new TrieNode();
            }
            root = root.childNodes[character - 'a'];
        }
        root.completeWord = true;
    }

    static class TrieNode {
        TrieNode[] childNodes = new TrieNode[26];
        boolean completeWord = false;
    }
}