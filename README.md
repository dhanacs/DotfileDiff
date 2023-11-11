## DotfileDiff
DotfileDiff is a utility designed for comparing two DOT files, revealing commonalities and differences in the edges they define. This can be particularly useful for understanding the structural disparities between two directed graphs.

The utility parses the DOT files and identifies edges in each file. It then highlights the edges common to both files, as well as those exclusive to each file. The output is structured to provide a clear understanding of the overlapping and unique relationships between the two graphs.

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

### Note
The utility also has methods to identify unique nodes in each file.
