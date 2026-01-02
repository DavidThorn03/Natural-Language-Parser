# Natural Language Parser
A recursive descent parser implementing context-free grammar validation and subject-verb agreement checking.
## Problem Statement
Validate English sentences following a specific grammar pattern with both syntactic structure checking and morphological agreement (singular/plural consistency).
## Grammar
### Sentence Pattern
The/A king(s) like(s)/dislike(s) the new cat
### Rules
- S → NP-SUB VP
- NP-SUB → DT NN-PER | DT NNS-PER
- NP-OBJ → DT JJ NN-OBJ  
- VP → VB NP-OBJ
### Lexical Categories

- DT: Determiner (the, a)
- NN-PER: Singular person noun (king)
- NNS-PER: Plural person noun (kings)
- NN-OBJ: Singular object noun (cat)
- VB: Verb (like/likes, dislike/dislikes)
- JJ: Adjective (new)

## Algorithm
### Two-Stage Validation

- Syntactic: Verifies sentence follows grammar rules via depth-first tree search
- Morphological: Checks singular/plural agreement between subjects and verbs

### Recursive Descent
```
javaCheckResult check(POS pos, ArrayList<Word> sentence, int index) {
    if (pos.isWord()) {
        return sentence.get(index).matches(pos) ? success : failure;
    }
    
    // Try each grammar rule matching this POS
    for (Rule rule : rules) {
        if (allChildrenMatch(rule)) return success;
    }
    
    return failure;
}
```

## Example

### Valid Input
```
"The king likes the new cat"
```

**Output:**
```
Parse successful

Bracket Structure:
[S[NP-SUB[DT[The]][NN-PER[king]]][VP[VB[likes]][NP-OBJ[DT[the]][JJ[new]][NN-OBJ[cat]]]]]

Tree Structure:
|--S
  |--NP-SUB
    |--DT: The
    |--NN-PER: king
  |--VP
    |--VB: likes
    |--NP-OBJ
      |--DT: the
      |--JJ: new
      |--NN-OBJ: cat
```

### Invalid Inputs
```
"The king like the new cat"  → Number agreement error
"The cat likes the new king" → Parse failed (wrong noun types)
```

## Results

- ✅ Successfully validates all 16 grammatical sentence variations
- ✅ Correctly rejects invalid constructions (wrong noun types, number disagreement)
- ✅ Generates bracketed phrase structure and visual tree representation

## Technologies

Java • Recursive Algorithms • Tree Data Structures • OOP
