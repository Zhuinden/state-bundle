# Change log

-State Bundle 1.2.2 (2020-01-30)
--------------------------------
- Fixed that `equals` only checked up to the number of keys found in the left side of the operation.

-State Bundle 1.2.1 (2018-07-26)
--------------------------------
- Fixed that `emptyStateBundle.equals(nonEmptyStateBundle)` incorrectly returned true.

-State Bundle 1.2.0 (2017-09-27)
--------------------------------
- Added method `copyToBundle(Bundle)` which can copy the contents of a StateBundle into an android.os.Bundle.

-State Bundle 1.1.5 (2017-05-14)
--------------------------------
- Better error message when `bundle.putAll()` is called with `null` (invalid) argument.

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