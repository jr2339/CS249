package p7_package;

public class StateDataClass implements java.lang.Comparable<StateDataClass> {
    private int population;

    private String state;

    /**
     * Default constructor, initializes all state data to default values
     */
    public StateDataClass()
    {
        state = "";

        population = 0;
    }

    /**
     * Initialization constructor, initializes state data to default values
     *
     * @param stateName String value with state name
     *
     * @param inPopulation integer population for state
     */
    public StateDataClass( String stateName,
                           int inPopulation )
    {
        state = stateName;

        population = inPopulation;

    }

    /**
     * Initialization constructor, initializes state data to default values
     *
     * @param copiedSC StateDataClass object to be copied
     */
    public StateDataClass( StateDataClass copiedSC )
    {
        state = copiedSC.state;

        population = copiedSC.population;
    }

    /**
     * Provides required method for comparing this object to another
     * StateDataClass object
     * <p>
     * Note: uses .length to get length of state string
     * <p>
     * Note: uses toLowerCase to test all characters as lower case
     * <p>
     * Note: returns negative value (not necessarily -1) if this data is less
     * than other data; returns positive value (not necessarily +1) if this
     * data is greater than other data; returns zero if this item and other
     * item are alphabetically equal and the same length
     *
     * @param other object of StateDataClass with which to compare
     */
    public int compareTo( StateDataClass other )
    {
        int index = 0;

        char str0neLetter;

        char strTwoLetter;

        int alphabetDifference;

        while( index < this.state.length() && index < other.state.length() )
        {
            str0neLetter = toLowerCase( this.state.charAt( index ) );

            strTwoLetter = toLowerCase( other.state.charAt( index ) );

            alphabetDifference = str0neLetter - strTwoLetter;

            if( alphabetDifference != 0 )
            {

                return alphabetDifference;

            }

            index++;

        }

        return this.state.length() - other.state.length();
    }

    /**
     * Changes character to lower case only if character was originally
     * an upper case letter
     *
     * @param testChar Character to be tested, if it is upper case it will
     * be converted to lower case; otherwise the testChar
     * will be returned unchanged
     *
     * @return returns the lower case version of a letter if it was an
     * upper case letter; otherwise, the character is returned unchanged
     */
    public char toLowerCase( char testChar )
    {
        if( testChar >= 'A' && testChar <= 'Z' )
        {
            testChar = ( char )( testChar - 'A' + 'a' );
        }

        return testChar;
    }

    /**
     * Overrides Object.toString, provides raw data from object
     *
     * Output: "State Name: sssss, Population: ppppp", where sssss is
     * state and ppppp is population
     *
     * @overrides toString in class java.lang.Object
     */
    public String toString()
    {
        return "State Name: " + state + ", Population: "  + population;
    }
}
