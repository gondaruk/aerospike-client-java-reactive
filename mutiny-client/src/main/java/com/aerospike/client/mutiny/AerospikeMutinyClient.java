package com.aerospike.client.mutiny;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.BatchRead;
import com.aerospike.client.BatchRecord;
import com.aerospike.client.BatchResults;
import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Operation;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.CTX;
import com.aerospike.client.cluster.Node;
import com.aerospike.client.policy.BatchDeletePolicy;
import com.aerospike.client.policy.BatchPolicy;
import com.aerospike.client.policy.BatchWritePolicy;
import com.aerospike.client.policy.InfoPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.IndexCollectionType;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.query.KeyRecord;
import com.aerospike.client.query.Statement;
import com.aerospike.client.reactor.AerospikeReactorClient;
import com.aerospike.client.reactor.IAerospikeReactorClient;
import com.aerospike.client.reactor.dto.KeyExists;
import com.aerospike.client.reactor.dto.KeyObject;
import com.aerospike.client.reactor.dto.KeysExists;
import com.aerospike.client.reactor.dto.KeysRecords;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;
import java.util.Map;
import mutiny.zero.flow.adapters.AdaptersToFlow;
import org.reactivestreams.Publisher;

public class AerospikeMutinyClient implements IAerospikeMutinyClient {

    private final IAerospikeReactorClient aerospikeReactorClient;

    /**
     * @param aerospikeClient the {@link com.aerospike.client.AerospikeClient} instance
     */
    public AerospikeMutinyClient(IAerospikeClient aerospikeClient) {
        this.aerospikeReactorClient = new AerospikeReactorClient(aerospikeClient);
    }

    @Override
    public void close() {
        aerospikeReactorClient.getAerospikeClient().close();
    }

    @Override
    public final Uni<KeyRecord> get(Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(key));
    }

    @Override
    public final Uni<KeyRecord> get(Policy policy, Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(policy, key));
    }

    @Override
    public final Uni<KeyRecord> get(Policy policy, Key key, String[] binNames) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(policy, key, binNames));
    }

    @Override
    public final Uni<KeysRecords> get(Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(keys));
    }

    @Override
    public final Uni<KeysRecords> get(BatchPolicy policy, Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(policy, keys));
    }

    @Override
    public final Uni<List<BatchRead>> get(List<BatchRead> records) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(records));
    }

    @Override
    public final Uni<List<BatchRead>> get(BatchPolicy policy, List<BatchRead> records) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(policy, records));
    }

    @Override
    public final Uni<KeysRecords> get(Key[] keys, Operation... operations) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(keys, operations));
    }

    @Override
    public final Uni<KeysRecords> get(BatchPolicy policy, Key[] keys, Operation... operations) throws AerospikeException {
        return asUni(aerospikeReactorClient.get(policy, keys, operations));
    }

    @Override
    public final Multi<BatchRead> getMulti(List<BatchRead> records) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(records));
    }

    @Override
    public final Multi<BatchRead> getMulti(BatchPolicy policy, List<BatchRead> records) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(policy, records));
    }

    @Override
    public final Multi<KeyRecord> getMulti(Key[] keys) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(keys));
    }

    @Override
    public final Multi<KeyRecord> getMulti(BatchPolicy policy, Key[] keys) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(policy, keys));
    }

    @Override
    public final Multi<KeyRecord> getMulti(Key[] keys, Operation... operations) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(keys, operations));
    }

    @Override
    public final Multi<KeyRecord> getMulti(BatchPolicy policy, Key[] keys, Operation... operations) throws AerospikeException {
        return asMulti(aerospikeReactorClient.getFlux(policy, keys, operations));
    }

    @Override
    public final Uni<KeyRecord> getHeader(Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.getHeader(key));
    }

    @Override
    public final Uni<KeyRecord> getHeader(Policy policy, Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.getHeader(policy, key));
    }

    @Override
    public final Uni<KeysRecords> getHeaders(Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.getHeaders(keys));
    }

    @Override
    public final Uni<KeysRecords> getHeaders(BatchPolicy policy, Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.getHeaders(policy, keys));
    }

    @Override
    public final Uni<Key> touch(Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.touch(key));
    }

    @Override
    public final Uni<Key> touch(WritePolicy policy, Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.touch(policy, key));
    }

    @Override
    public final Uni<Key> exists(Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.exists(key));
    }

    @Override
    public final Uni<Key> exists(Policy policy, Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.exists(policy, key));
    }

    @Override
    public final Uni<KeysExists> exists(Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.exists(keys));
    }

    @Override
    public final Uni<KeysExists> exists(BatchPolicy policy, Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.exists(policy, keys));
    }

    @Override
    public final Multi<KeyExists> existsMulti(Key[] keys) throws AerospikeException {
        return asMulti(aerospikeReactorClient.existsFlux(keys));
    }

    @Override
    public final Multi<KeyExists> existsMulti(BatchPolicy policy, Key[] keys) throws AerospikeException {
        return asMulti(aerospikeReactorClient.existsFlux(policy, keys));
    }

    @Override
    public final Uni<Key> put(Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.put(key, bins));
    }

    @Override
    public final Uni<Key> put(WritePolicy policy, Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.put(policy, key, bins));
    }

    @Override
    public final Uni<Key> append(Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.append(key, bins));
    }

    @Override
    public final Uni<Key> append(WritePolicy policy, Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.append(policy, key, bins));
    }

    @Override
    public final Uni<Key> prepend(Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.prepend(key, bins));
    }

    @Override
    public final Uni<Key> prepend(WritePolicy policy, Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.prepend(policy, key, bins));
    }

    @Override
    public final Uni<Key> add(Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.add(key, bins));
    }

    @Override
    public final Uni<Key> add(WritePolicy policy, Key key, Bin... bins) throws AerospikeException {
        return asUni(aerospikeReactorClient.add(policy, key, bins));
    }

    @Override
    public final Uni<Key> delete(Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.delete(key));
    }

    @Override
    public final Uni<Key> delete(WritePolicy policy, Key key) throws AerospikeException {
        return asUni(aerospikeReactorClient.delete(policy, key));
    }

    @Override
    public Uni<BatchResults> delete(BatchPolicy batchPolicy, BatchDeletePolicy deletePolicy,
                                    Key[] keys) throws AerospikeException {
        return asUni(aerospikeReactorClient.delete(batchPolicy, deletePolicy, keys));
    }

    @Override
    public final Uni<KeyRecord> operate(Key key, Operation... operations) throws AerospikeException {
        return asUni(aerospikeReactorClient.operate(key, operations));
    }

    @Override
    public final Uni<KeyRecord> operate(WritePolicy policy, Key key, Operation... operations) throws AerospikeException {
        return asUni(aerospikeReactorClient.operate(policy, key, operations));
    }

    @Override
    public Uni<BatchResults> operate(BatchPolicy batchPolicy, BatchWritePolicy writePolicy, Key[] keys,
                                     Operation... ops) throws AerospikeException {
        return asUni(aerospikeReactorClient.operate(batchPolicy, writePolicy, keys, ops));
    }

    @Override
    public Uni<Boolean> operate(BatchPolicy policy, List<BatchRecord> records) throws AerospikeException {
        return asUni(aerospikeReactorClient.operate(policy, records));
    }

    @Override
    public final Multi<KeyRecord> query(Statement statement) throws AerospikeException {
        return asMulti(aerospikeReactorClient.query(statement));
    }

    @Override
    public final Multi<KeyRecord> query(QueryPolicy policy, Statement statement) throws AerospikeException {
        return asMulti(aerospikeReactorClient.query(policy, statement));
    }

    @Override
    public final Multi<KeyRecord> scanAll(String namespace, String setName, String... binNames) throws AerospikeException {
        return asMulti(aerospikeReactorClient.scanAll(namespace, setName, binNames));
    }

    @Override
    public final Multi<KeyRecord> scanAll(ScanPolicy policy, String namespace, String setName, String... binNames) throws AerospikeException {
        return asMulti(aerospikeReactorClient.scanAll(namespace, setName, binNames));
    }

    @Override
    public final Uni<KeyObject> execute(Key key, String packageName, String functionName, Value... functionArgs) throws AerospikeException {
        return asUni(aerospikeReactorClient.execute(key, packageName, functionName, functionArgs));
    }

    @Override
    public final Uni<KeyObject> execute(WritePolicy policy, Key key,
                                        String packageName, String functionName, Value... functionArgs) throws AerospikeException {
        return asUni(aerospikeReactorClient.execute(policy, key, packageName, functionName, functionArgs));
    }

    @Override
    public Uni<String> info(InfoPolicy infoPolicy, Node node, String command) {
        return asUni(aerospikeReactorClient.info(infoPolicy, node, command));
    }

    @Override
    public Uni<Map<String, String>> info(InfoPolicy infoPolicy, Node node, List<String> commands) {
        return asUni(aerospikeReactorClient.info(infoPolicy, node, commands));
    }

    @Override
    public Uni<Void> createIndex(Policy policy,
                                 String namespace, String setName, String indexName, String binName,
                                 IndexType indexType, IndexCollectionType indexCollectionType, CTX... ctx) {
        return asUni(aerospikeReactorClient.createIndex(policy, namespace, setName, indexName, binName, indexType, indexCollectionType, ctx));
    }

    @Override
    public Uni<Void> dropIndex(Policy policy, String namespace, String setName, String indexName) {
        return asUni(aerospikeReactorClient.dropIndex(policy, namespace, setName, indexName));
    }

    @Override
    public IAerospikeClient getAerospikeClient() {
        return aerospikeReactorClient.getAerospikeClient();
    }

    @Override
    public Policy getReadPolicyDefault() {
        return aerospikeReactorClient.getReadPolicyDefault();
    }

    @Override
    public WritePolicy getWritePolicyDefault() {
        return aerospikeReactorClient.getWritePolicyDefault();
    }

    @Override
    public ScanPolicy getScanPolicyDefault() {
        return aerospikeReactorClient.getScanPolicyDefault();
    }

    @Override
    public QueryPolicy getQueryPolicyDefault() {
        return aerospikeReactorClient.getQueryPolicyDefault();
    }

    @Override
    public BatchPolicy getBatchPolicyDefault() {
        return aerospikeReactorClient.getBatchPolicyDefault();
    }

    @Override
    public InfoPolicy getInfoPolicyDefault() {
        return aerospikeReactorClient.getInfoPolicyDefault();
    }

    /**
     * Private method to convert any Publisher (Mono or Flux) into Uni
     */
    private static <T> Uni<T> asUni(Publisher<T> publisher) {
        return Uni.createFrom().publisher(AdaptersToFlow.publisher(publisher));
    }

    /**
     * Private method to convert any Publisher (Mono or Flux) into Multi
     */
    private static <T> Multi<T> asMulti(Publisher<T> publisher) {
        return Multi.createFrom().publisher(AdaptersToFlow.publisher(publisher));
    }
}
