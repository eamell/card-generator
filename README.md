# Term Card Generator

Web service that generates flash and flip cards in PDF files.  A flash card is a card where one side has the term and
the other has the definition.  A flip card is a card where the term and the definition are both on the same side of
the card.

Flash cards will be built with the intention of printing it double-sided flipping on the long edge.

For both sets of cards a card and page size must be specified.  The card size must be smaller than or equal to the page
size.  Pages will contain as many cards as will fit on it in a tabular format where the page size will be flipped
between portrait or landscape depending on which would hold more.  Unless specified all pages will have a default 
margin of 32 points (.5 inches or 12.7 mm).

**NOTE:** A margin of less than 32 points may limit the printers that will be able to print the resulting PDF, since
the intent is for these files to be printed the default is recommended.

Size will be specified in points where there are `1 inch = 72 points = 25.4 mm` or `1 point = 1/72 inch = 0.35 mm`.
Some standard sizes are shown below:

Size                          | Width    | Height
------------------------------|----------|--------
Letter - 8.5 x 11 in          | 612      | 792
Legal - 8.5 x 14 in           | 612      | 1008
Tabloid - 11 x 17 in          | 1008     | 1224
Index Card - 3 x 5 in         | 216      | 360
US Business Card - 3.5 x 2 in | 252      | 144
A3 - 297 x 420 mm             | 842      | 1191
A4 - 210 x 297 mm             | 595      | 842
A7 - 74 x 105 mm              | 210      | 298
A10 - 37 x 52 mm              | 105      | 147

So a common example could be to have letter paper with cards that are index card sized.  This would allow for 4 cards
per sheet when printed portrait.
