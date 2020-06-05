# Cache Simulator

Configurable CPU cache simulator with LRU replacement policy. Full description [here](https://github.com/radinshayanfar/ca_cache_simulator/blob/master/project_description.pdf).

## Input pattern
```text
<block size> - <architecture> - <associativity> - <write hit policy> - <write miss policy>
<cache(s) size>
<request type> <address> <optional description - will be ignored>
```
\<block size>: Should be in power of 2 <br>
\<architecture>: `0` for von Neumann (unified I-D cache) - `1` for Harvard (split I-D cache) <br>
\<associativity>: Should be in power of 2 <br>
\<write hit policy>: `wt` for Write Through - `wb` for Write Back <br>
\<write miss policy>: `wa` for Write Allocate - `nw` for No Write Allocate (Write Around) <br>
\<cache(s) size>: Should be in power of 2. Separated by ` - ` in case of Harvard architecture <br>
\<request type>:
<table>
<thead>
  <tr>
    <th>type</th>
    <th>description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>0</td>
    <td>data read request</td>
  </tr>
  <tr>
    <td>1</td>
    <td>data write request</td>
  </tr>
  <tr>
    <td>2</td>
    <td>instruction read request</td>
  </tr>
</tbody>
</table>

## Sample input
```text
16 - 1 - 1 - wb - wa
128 - 128
0 00000 data read miss (compulsory)
2 10000 instruction miss (compulsory, replaces 00000 if assoc=1 & unified)
2 20000 instruction miss (compulsory, replaces 00000 if assoc=2 & unified)
2 30000 instruction miss (compulsory, replaces 00000 if assoc=2 & unified)
2 40000 instruction miss (compulsory, replaces 00000 if assoc=4 & unified)
0 00000 data read miss (miss if assoc=1 & unified)
2 10001 instruction miss (miss if assoc=1 & unified)
```

## Sample output
```text
***CACHE SETTINGS***
Split I- D-cache
I-cache size: 128
D-cache size: 128
Associativity: 1
Block size: 16
Write policy: WRITE BACK
Allocation policy: WRITE ALLOCATE

***CACHE STATISTICS***
INSTRUCTIONS
accesses: 5
misses: 5
miss rate: 1.0000 (hit rate 0.0000)
replace: 4
DATA
accesses: 2
misses: 1
miss rate: 0.5000 (hit rate 0.5000)
replace: 0
TRAFFIC (in words)
demand fetch: 24
copies back: 0
```