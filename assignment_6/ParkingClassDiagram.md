```
┌──────────────────────────────────────────────────────────┐
│                      ParkingOffice                       │
├──────────────────────────────────────────────────────────┤
│ - name : String                                          │
│ - address : Address                                      │
│ - customers : List<Customer>                             │
│ - cars : List<Car>                                       │
│ - lots : List<ParkingLot>                                │
│ - charges : List<ParkingCharge>                          │
├──────────────────────────────────────────────────────────┤
│ + ParkingOffice(name : String, address : Address)        │
│ + getName() : String                                     │
│ + getAddress() : Address                                 │
│ + getCustomers() : List<Customer>                        │
│ + getCars() : List<Car>                                  │
│ + getLots() : List<ParkingLot>                           │
│ + getCharges() : List<ParkingCharge>                     │
│ + register(name : String, address : Address,             │
│       phone : String) : Customer                         │
│ + register(c : Customer, license : String,               │
│       t : CarType) : Car                                 │
│ + getCustomer(name : String) : Customer                  │
│ + addLot(lot : ParkingLot) : void                        │
│ + addCharge(charge : ParkingCharge) : Money              │
│ + park(car : Car, lot : ParkingLot) : Money              │
│ - getCar(permit : String) : Car                          │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                      ParkingCharge                       │
├──────────────────────────────────────────────────────────┤
│ - permitId : String                                      │
│ - lotId : String                                         │
│ - incurred : Instant                                     │
│ - amount : Money                                         │
├──────────────────────────────────────────────────────────┤
│ + ParkingCharge(permitId : String, lotId : String,       │
│       incurred : Instant, amount : Money)                │
│ + getPermitId() : String                                 │
│ + getLotId() : String                                    │
│ + getIncurred() : Instant                                │
│ + getAmount() : Money                                    │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                          Money                           │
├──────────────────────────────────────────────────────────┤
│ - cents : long                                           │
├──────────────────────────────────────────────────────────┤
│ + Money(cents : long)                                    │
│ + getCents() : long                                      │
│ + getDollars() : double                                  │
│ + times(factor : double) : Money                         │
│ + equals(other : Object) : boolean                       │
│ + hashCode() : int                                       │
│ + toString() : String                                    │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                        ParkingLot                        │
├──────────────────────────────────────────────────────────┤
│ - COMPACT_DISCOUNT : double = 0.8             {static}   │
│ - lotId : String                                         │
│ - address : Address                                      │
│ - capacity : int                                         │
│ - dailyRate : Money                                      │
│ - entryTimes : Map<String, Instant>                      │
├──────────────────────────────────────────────────────────┤
│ + ParkingLot(lotId : String, address : Address,          │
│       capacity : int, dailyRate : Money)                 │
│ + getLotId() : String                                    │
│ + getAddress() : Address                                 │
│ + getCapacity() : int                                    │
│ + getDailyRate() : Money                                 │
│ + getOccupancy() : int                                   │
│ + isFull() : boolean                                     │
│ + getRate(car : Car) : Money                             │
│ + entry(car : Car) : ParkingCharge                       │
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
ParkingOffice ◆─────> Address              (has a)
ParkingOffice 1 ────> 0..* Customer        (registers)
ParkingOffice 1 ────> 0..* Car             (registers)
ParkingOffice 1 ────> 0..* ParkingLot      (manages)
ParkingOffice 1 ────> 0..* ParkingCharge   (records)
ParkingCharge ◆─────> Money                (has an amount)
ParkingCharge ┄┄┄┄┄┄> Car                  (permitId holds the car's permit)
ParkingCharge ┄┄┄┄┄┄> ParkingLot           (lotId holds the lot's id)
ParkingLot ◆────────> Address              (has a)
ParkingLot ◆────────> Money                (has a daily rate)
ParkingLot ┄┄┄┄┄┄┄┄┄> ParkingCharge        (entry(Car) creates)
ParkingLot ┄┄┄┄┄┄┄┄┄> Car                  (entry(Car) records the car's entry time)
Customer ◆──────────> Address              (has a)
Customer 1 ─────────> 0..* Car             (registers / creates)
Car ────────────────> CarType              (uses)
Car ┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄> Customer             (owner holds the customerId)
```
