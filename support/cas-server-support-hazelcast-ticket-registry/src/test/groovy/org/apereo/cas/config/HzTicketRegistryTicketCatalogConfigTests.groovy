package org.apereo.cas.config

import org.apereo.cas.configuration.CasConfigurationProperties
import org.apereo.cas.ticket.*
import spock.lang.Specification

/**
 * @author Dmitriy Kopylenko
 */
class HzTicketRegistryTicketCatalogConfigTests extends Specification {

    def hzTicketCatalogConfigurer = new HazelcastTicketRegistryTicketCatalogConfiguration(casProperties: new CasConfigurationProperties())

    def ticketCatalog = new DefaultTicketCatalog()

    static TGT_TICKET = new TicketGrantingTicketImpl(id: 'TGT-1976')

    static ST_TICKET = new ServiceTicketImpl(id: 'ST-1976')

    static PGT_TICKET = new ProxyGrantingTicketImpl(id: 'PGT-1976')

    static PT_TICKET = new ProxyTicketImpl(id: 'PT-1976')

    def setup() {
        this.hzTicketCatalogConfigurer.configureTicketCatalog(ticketCatalog)
    }

    def "should configure correctly TGT ticket definition"() {
        when: 'HZ Ticket Catalog configuration is run and definition is retrieved'
        def ticketDefinition = ticketCatalog.find(TGT_TICKET)

        then: 'Correct TGT ticket definition is registered with a global `TicketCatalog`'
        assert ticketDefinition.implementationClass == TicketGrantingTicketImpl
        assert ticketDefinition.prefix == 'TGT'
        assert ticketDefinition.properties.storageName == 'ticketGrantingTicketsCache'
    }

    def "should configure correctly ST ticket definition"() {
        when: 'HZ Ticket Catalog configuration is run and definition is retrieved'
        def ticketDefinition = ticketCatalog.find(ST_TICKET)

        then: 'Correct ST ticket definition is registered with a global `TicketCatalog`'
        assert ticketDefinition.implementationClass == ServiceTicketImpl
        assert ticketDefinition.prefix == 'ST'
        assert ticketDefinition.properties.storageName == 'serviceTicketsCache'
    }

    def "should configure correctly PGT ticket definition"() {
        when: 'HZ Ticket Catalog configuration is run and definition is retrieved'
        def ticketDefinition = ticketCatalog.find(PGT_TICKET)

        then: 'Correct PGT ticket definition is registered with a global `TicketCatalog`'
        assert ticketDefinition.implementationClass == ProxyGrantingTicketImpl
        assert ticketDefinition.prefix == 'PGT'
        assert ticketDefinition.properties.storageName == 'proxyGrantingTicketsCache'
    }

    def "should configure correctly PT ticket definition"() {
        when: 'HZ Ticket Catalog configuration is run and definition is retrieved'
        def ticketDefinition = ticketCatalog.find(PT_TICKET)

        then: 'Correct PT ticket definition is registered with a global `TicketCatalog`'
        assert ticketDefinition.implementationClass == ProxyTicketImpl
        assert ticketDefinition.prefix == 'PT'
        assert ticketDefinition.properties.storageName == 'proxyTicketsCache'
    }
}
