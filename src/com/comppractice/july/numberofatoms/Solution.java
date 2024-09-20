package com.comppractice.july.numberofatoms;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countOfAtoms("Mg(H2O)"));
    }

    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> molecule = new Stack<>();
        molecule.push(new HashMap<>());

        StringBuilder atom = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            if ((atom.length() == 0)
                    && Character.isAlphabetic(formula.charAt(i))
                    && Character.isUpperCase(formula.charAt(i))) {
                atom = getStringBuilder(formula, molecule, atom, i);
            } else if (Character.isAlphabetic(formula.charAt(i))
                    && Character.isLowerCase(formula.charAt(i))) {
                atom = getStringBuilder(formula, molecule, atom, i);
            } else if (Character.isAlphabetic(formula.charAt(i))
                    && Character.isUpperCase(formula.charAt(i))) {
                Map<String, Integer> stackAtom = molecule.peek();
                stackAtom.put(atom.toString(), stackAtom.getOrDefault(atom.toString(), 0) + 1);
                atom = new StringBuilder();
                atom.append(formula.charAt(i));
            } else if (Character.isDigit(formula.charAt(i))) {
                StringBuilder number = new StringBuilder();
                while (formula.length() > i) {
                    if (Character.isDigit(formula.charAt(i))) {
                        number.append(formula.charAt(i));
                        i++;
                    } else {
                        i--;
                        break;
                    }
                }
                Map<String, Integer> stackAtom = molecule.peek();
                stackAtom.put(atom.toString(),
                        stackAtom.getOrDefault(atom.toString(), 0) + Integer.parseInt(
                                number.toString()));
                atom = new StringBuilder();
            } else if (formula.charAt(i) == '(') {
                if (atom.length() != 0) {
                    Map<String, Integer> stackAtom = molecule.peek();
                    stackAtom.put(atom.toString(), stackAtom.getOrDefault(atom.toString(), 0) + 1);
                    atom = new StringBuilder();
                }
                molecule.push(new HashMap<>());
            } else if (formula.charAt(i) == ')') {
                if (atom.length() != 0) {
                    Map<String, Integer> stackAtom = molecule.peek();
                    stackAtom.put(atom.toString(), stackAtom.getOrDefault(atom.toString(), 0) + 1);
                }
                Map<String, Integer> subMolecule = molecule.pop();
                if (formula.length() > i + 1 && Character.isDigit(formula.charAt(i + 1))) {
                    StringBuilder number = new StringBuilder();
                    while (formula.length() > i + 1) {
                        if (Character.isDigit(formula.charAt(i + 1))) {
                            number.append(formula.charAt(i + 1));
                            i++;
                        } else {
                            i--;
                            break;
                        }
                    }
                    Integer subMultiplier = Integer.parseInt(number.toString());
                    subMolecule.replaceAll((k, v) -> v * subMultiplier);
                    i++;
                }
                //merging
                Map<String, Integer> lowerMolecule = molecule.peek();
                for (Map.Entry<String, Integer> subMoleculeEntry : subMolecule.entrySet()) {
                    lowerMolecule.put(subMoleculeEntry.getKey(),
                            lowerMolecule.getOrDefault(subMoleculeEntry.getKey(), 0) + subMoleculeEntry.getValue());
                }
                atom = new StringBuilder();
            }

        }
        StringBuilder result = new StringBuilder();
        molecule.peek().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            result.append(entry.getKey());
            result.append(entry.getValue() == 1 ? "" : entry.getValue());
        });
        return result.toString();
    }

    private StringBuilder getStringBuilder(final String formula, final Stack<Map<String, Integer>> molecule,
            StringBuilder atom, final int i) {
        atom.append(formula.charAt(i));
        if (i == formula.length() - 1) {
            Map<String, Integer> stackAtom = molecule.peek();
            stackAtom.put(atom.toString(), stackAtom.getOrDefault(atom.toString(), 0) + 1);
            atom = new StringBuilder();
        }
        return atom;
    }
}