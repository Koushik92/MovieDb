# MovieDb

**[Programming Language]** - Kotlin

**[Architecture Used]** - Model - View - ViewModel (MVVM)

**[Architecture Components Used**] - Mutable Live Data , Data Binding Library and Kotlin KTX

**[]** - 

**[]** - 


**[Architecture Implementation Details]** - 
- Activity (User Interaction layer) made completely free from doing business logic .
- View Model takes care of processing all business logic . It is provided from music sdk 
- Music SDK  takes care of getting data from Media Player Native Music Library . Once it gets data it passes 
  view model api methods and data holders updated
- Once View Model receives data ,it process the data and return back to activity through Mutable Live data .
- As soon as new data available , activity observers are invoked and displays the data to user 
- Broadcast receivers are used for Notification builders 

**[Functional Implementation Details]** - 
- Play , Pause and Stop functionalities of the Songs implemented .
