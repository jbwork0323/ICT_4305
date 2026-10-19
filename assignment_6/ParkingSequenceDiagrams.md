# Parking System Sequence Diagrams

## Use Case 1: Customer registers a Car and receives a Parking Permit

```
 :Customer(actor)     :ParkingOffice              c:Customer                 :Car
       │                    │                         │                        │
       │ register(name,     │                         │                        │
       │   address, phone)  │                         │                        │
       │───────────────────>│                         │                        │
       │                    │ new Customer(id, name,  │                        │
       │                    │   address, phone)       │                        │
       │                    │────────────────────────>│                        │
       │                    │ customers.add(c)        │                        │
       │                    │──┐                      │                        │
       │                    │<─┘                      │                        │
       │        c           │                         │                        │
       │<───────────────────│                         │                        │
       │                    │                         │                        │
       │ register(c, license, t)                      │                        │
       │───────────────────>│                         │                        │
       │                    │ [c not registered]      │                        │
       │                    │   throw IllegalArgumentException                 │
       │                    │                         │                        │
       │                    │ register(license, t)    │                        │
       │                    │────────────────────────>│                        │
       │                    │                         │ generatePermit()       │
       │                    │                         │──┐                     │
       │                    │                         │<─┘ permit "P0001"      │
       │                    │                         │                        │
       │                    │                         │ new Car(permit,        │
       │                    │                         │   now + 1 year,        │
       │                    │                         │   license, t, id)      │
       │                    │                         │───────────────────────>│
       │                    │                         │ cars.add(car)          │
       │                    │                         │──┐                     │
       │                    │                         │<─┘                     │
       │                    │          car            │                        │
       │                    │<────────────────────────│                        │
       │                    │ cars.add(car)           │                        │
       │                    │──┐                      │                        │
       │                    │<─┘                      │                        │
       │  car (holds permit)│                         │                        │
       │<───────────────────│                         │                        │
       │                    │                         │                        │
```

The Customer receives the parking permit as part of the returned `Car`
(`car.getPermit()` and `car.getPermitExpiration()`).

## Use Case 2: Car enters a Parking Lot and the Customer incurs a charge

```
  car:Car          :ParkingOffice          lot:ParkingLot        :ParkingCharge
     │                   │                        │                      │
     │ park(car, lot)    │                        │                      │
     │──────────────────>│                        │                      │
     │                   │ [lot not managed]      │                      │
     │                   │   throw IllegalArgumentException              │
     │                   │                        │                      │
     │                   │ entry(car)             │                      │
     │                   │───────────────────────>│                      │
     │                   │                        │ [permit expired,     │
     │                   │                        │  already in lot,     │
     │                   │                        │  or lot full]        │
     │                   │                        │  throw IllegalStateException
     │                   │                        │                      │
     │                   │                        │ entryTimes.put(      │
     │                   │                        │   permit, now)       │
     │                   │                        │──┐                   │
     │                   │                        │<─┘                   │
     │                   │                        │ getRate(car)         │
     │                   │                        │──┐ COMPACT: 80% of   │
     │                   │                        │<─┘ dailyRate (Money) │
     │                   │                        │                      │
     │                   │                        │ new ParkingCharge(   │
     │                   │                        │   permit, lotId,     │
     │                   │                        │   now, amount)       │
     │                   │                        │─────────────────────>│
     │                   │        charge          │                      │
     │                   │<───────────────────────│                      │
     │                   │                        │                      │
     │                   │ addCharge(charge)      │                      │
     │                   │──┐                     │                      │
     │                   │  │ getCar(charge.getPermitId())               │
     │                   │  │ charges.add(charge) │                      │
     │                   │<─┘ amount              │                      │
     │  amount : Money   │                        │                      │
     │<──────────────────│                        │                      │
     │                   │                        │                      │
```

The charge is linked to the Customer through the permit: the `ParkingCharge`
holds the `permitId`, the `Car` with that permit holds the owner's `customerId`.
