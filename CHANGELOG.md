# Change log

-State Bundle 1.1.4 (2017-04-16)
--------------------------------
- Make `StateBundle(Parcel in)` constructor public.

-State Bundle 1.1.3 (2017-04-14)
--------------------------------
- All `put*` methods return `this`.
- `clear()` also returns `this`.
- `remove()` also returns `this`.

-State Bundle 1.1.1 (2017-04-14)
--------------------------------
- `toString()` will write `[]` instead of empty string, if the state bundle is empty.

-State Bundle 1.1.0 (2017-04-12)
--------------------------------
- Implemented `toString()` in a repeatable manner.
- Implemented custom `equals()`. Relies on stored elements to also correctly override `equals()`.
- Implemented custom `hashCode()`. Relies on stored elements to also correctly override `hashCode()`.

-State Bundle 1.0.1 (2017-03-20)
--------------------------------
MinSDK decreased to 1.

-State Bundle 1.0.0 (2017-03-06)
--------------------------------
Initial Release.