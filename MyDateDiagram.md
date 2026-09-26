┌──────────────────────────────────────────────────────────┐
│                          MyDate                          │
├──────────────────────────────────────────────────────────┤
│ - day : int                                              │
│ - month : int                                            │
│ - year : int                                             │
│ - julianNumber : int                                     │
├──────────────────────────────────────────────────────────┤
│ + MyDate()                                               │
│ + MyDate(day : int, month : int, year : int)             │
│ + MyDate(date : MyDate)                                  │
│ + getDay() : int                                         │
│ + getMonth() : int                                       │
│ + getYear() : int                                        │
│ + isLeapYear(year : int) : boolean            {static}   │
│ + getLastDayOfMonth(month : int, year : int) : int       │
│                                               {static}   │
│ - toJulianNumber(day : int, month : int, year : int)     │
│       : int                                   {static}   │
│ - fromJulianNumber() : int[]                             │
└──────────────────────────────────────────────────────────┘
