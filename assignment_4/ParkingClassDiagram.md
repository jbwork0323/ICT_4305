```
┌──────────────────────────────────────────────────────────┐
│                        ParkingLot                        │
├──────────────────────────────────────────────────────────┤
│ - lotId : String                                         │
│ - address : Address                                      │
│ - capacity : int                                         │
│ - entryTimes : Map<String, Instant>                      │
├──────────────────────────────────────────────────────────┤
│ + ParkingLot(lotId : String, address : Address,          │
│       capacity : int)                                    │
│ + getLotId() : String                                    │
│ + getAddress() : Address                                 │
│ + getCapacity() : int                                    │
│ + getOccupancy() : int                                   │
│ + isFull() : boolean                                     │
│ + entry(car : Car) : void                                │
│ + getEntryTime(car : Car) : Instant                      │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                     <<enumeration>>                      │
│                         CarType                          │
├──────────────────────────────────────────────────────────┤
│ COMPACT                                                  │
│ SUV                                                      │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                           Car                            │
├──────────────────────────────────────────────────────────┤
│ - permit : String                                        │
│ - permitExpiration : LocalDate                           │
│ - license : String                                       │
│ - type : CarType                                         │
│ - owner : String  (customer id)                          │
├──────────────────────────────────────────────────────────┤
│ + Car(permit : String, permitExpiration : LocalDate,     │
│       license : String, type : CarType, owner : String)  │
│ + getPermit() : String                                   │
│ + getPermitExpiration() : LocalDate                      │
│ + getLicense() : String                                  │
│ + getType() : CarType                                    │
│ + getOwner() : String                                    │
│ + isPermitValid(date : LocalDate) : boolean              │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                         Customer                         │
├──────────────────────────────────────────────────────────┤
│ - PERMIT_LENGTH_YEARS : int = 1               {static}   │
│ - nextPermitNumber : int = 1                  {static}   │
│ - customerId : String                                    │
│ - name : String                                          │
│ - address : Address                                      │
│ - phoneNumber : String                                   │
│ - cars : List<Car>                                       │
├──────────────────────────────────────────────────────────┤
│ + Customer(customerId : String, name : String,           │
│       address : Address, phoneNumber : String)           │
│ + getCustomerId() : String                               │
│ + getName() : String                                     │
│ + getAddress() : Address                                 │
│ + getPhoneNumber() : String                              │
│ + getCars() : List<Car>                                  │
│ + register(license : String, type : CarType) : Car       │
│ - generatePermit() : String                   {static}   │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                         Address                          │
├──────────────────────────────────────────────────────────┤
│ - streetAddress1 : String                                │
│ - streetAddress2 : String                                │
│ - city : String                                          │
│ - state : String                                         │
│ - zipCode : String                                       │
├──────────────────────────────────────────────────────────┤
│ + Address(streetAddress1 : String,                       │
│       streetAddress2 : String, city : String,            │
│       state : String, zipCode : String)                  │
│ + getStreetAddress1() : String                           │
│ + getStreetAddress2() : String                           │
│ + getCity() : String                                     │
│ + getState() : String                                    │
│ + getZipCode() : String                                  │
│ + getAddressInfo() : String                              │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘
```

## Relationships

```
Customer ◆──────────> Address        (has a)
ParkingLot ◆────────> Address        (has a)
Customer 1 ─────────> 0..* Car       (registers / creates)
Car ────────────────> CarType        (uses)
Car ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄> Customer       (owner holds the customerId)
ParkingLot ┄┄┄┄┄┄┄┄┄> Car            (entry(Car) records the car's entry time)
```
