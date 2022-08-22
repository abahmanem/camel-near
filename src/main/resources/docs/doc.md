# NEAR  producer component
Camel NEAR component for interacting with NEAR network using RPC calls (Java SDK)


* RPC Operations
---
## VALIDATORS
```java
from("direct:start").to("near://node_url?operation=VALIDATORS")
```
Returns the list of current validators
