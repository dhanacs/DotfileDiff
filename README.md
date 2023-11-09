## DotfileDiff
This utility provides functionality to compare two DOT files and discover unique names within them.

### Example Usage
### File A
```
digraph "A" {
    "a" -> "b";
    "a" -> "c";
    "a" -> "d";
    "d" -> "e";
    "d" -> "f";
    "d" -> "g";
}
```

### File B
```
digraph "A" {
    "a" -> "b";
    "a" -> "c";
    "a" -> "d";
    "h" -> "e";
    "h" -> "f";
    "h" -> "g";
}
```

### Output
```
Common edges present in both A & B

a->b
a->c
a->d


Present only in file A, not in file B

d->e
d->f
d->g


Present only in file B, not in file A

h->e
h->f
h->g
```
