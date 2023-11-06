// Print the given edges
// This function prints a list of edges.
def printEdges(edges) {
    edges.each { edge ->
        println(edge);
    }
}

// Print the given nodes
// This function prints a list of nodes.
def printNodes(nodes) {
    nodes.each { node ->
        println(node);
    }
}

// Get all the common entries (Intersection) between two arrays
// This function returns a list of common elements between two arrays.
def intersection(arrayA, arrayB) {
    def results = [];
    def mapA = [:];

    // Hash all the edges in arrayA
    arrayA.each { edge ->
        mapA[edge] = true;
    }

    // For each edge in arrayB, check if it is in the hash
    arrayB.each { edge ->
        if(mapA.containsKey(edge)) {
            results.add(edge);
        }
    }

    return results;
}

// Find the edges only in arrayA, not in arrayB (A - B)
// This function returns a list of elements present in arrayA but not in arrayB.
def difference(arrayA, arrayB) {
    def results = [];
    def intersectionArray = intersection(arrayA, arrayB);
    def intersectionMap = [:];

    // Hash all the edges common to both arrays
    intersectionArray.each { edge ->
        intersectionMap[edge] = true;
    }

    // For each edge in arrayA, check if it is not in the common edges hash; if not, add it to the result
    arrayA.each { edge ->
        if (!intersectionMap.containsKey(edge)) {
            results.add(edge);
        }
    }

    return results;
}

// Get two DOT files A and B and find the differences between them
def process(fileA, fileB) {
    def edgesA = [];
    def edgesB = [];
    def (nameA, extension1) = fileA.tokenize('.');
    def (nameB, extension2) = fileB.tokenize('.');

    try {
        edgesA = processDotFile(fileA);
        edgesB = processDotFile(fileB);

        println("Common edges present in both " + nameA + " & " + nameB);
        println("==========================================\n");
        def common = intersection(edgesA, edgesB);
        printEdges(common);

        println("\n");
        println("Present only in file " + nameA + ", not in file " + nameB);
        println("==========================================\n");
        def differenceAB = difference(edgesA, edgesB);
        printEdges(differenceAB);

        println("\n");
        println("Present only in file " + nameB + ", not in file " + nameA);
        println("==========================================\n");
        def differenceBA = difference(edgesB, edgesA);
        printEdges(differenceBA);

        println("\n");
        println("\n");
        println("Summary");
        println("common paths present in both " + nameA + " & " + nameB + " = " + common.size());
        println("paths present only in " + nameA + " = " + differenceAB.size());
        println("paths present only in " + nameB + " = " + differenceBA.size());
    } catch (e) {
        print("DiffChecker:: Exception processing DOT files : ${e}");
    }
}

// Get the string in-between double quotes
// This is a utility function to extract content within double quotes from a string.
def getStringInBetween(s) {
    def quote = '"';
    s = s.substring(s.indexOf(quote) + 1);
    s = s.substring(0, s.indexOf(quote));

    return s;
}

// Process the given DOT file
// 1. Take the DOT file Input
// 2. Parse & return the edges
def processDotFile(fileName) {
    def edgesMap = [:];
    def edges = [];
    def lines = new File(fileName).readLines();

    lines.each { line ->
        if (line.contains("->")) {
            def list = line.split("->");
            def u = list[0];
            def v = list[1];

            u = getStringInBetween(u);
            v = getStringInBetween(v);

            edge = u + "->" + v;
            if (!edgesMap.containsKey(edge)) {
                edges.add(edge);
                edgesMap[edge] = true;
            }
        }
    }

    return edges;
}

// Get unique libraries in the given DOT file
// 1. Take the DOT file Input
// 2. Parse & return the unique libraries
def uniqueLibraries(fileName) {
    def map = [:];
    def libraries = [];
    def lines = new File(fileName).readLines();

    lines.each { line ->
        if (line.contains("->")) {
            def list = line.split("->");
            def u = list[0];
            def v = list[1];

            u = getStringInBetween(u);
            v = getStringInBetween(v);

            if (!map.containsKey(u)) {
                libraries.add(u);
                map[u] = true;
            }

            if (!map.containsKey(v)) {
                libraries add(v);
                map[v] = true;
            }
        }
    }

    libraries.sort();
    return libraries;
}

// Compare the given two DOT files
// Output:
//   1. Common edges
//   2. Edges present only in file A
//   3. Edges present only in file B
process('V1.dot', 'V2.dot');

// The following lines are currently commented out and not executed.
// def a = uniqueLibraries("V0.dot");
// printNodes(a);
