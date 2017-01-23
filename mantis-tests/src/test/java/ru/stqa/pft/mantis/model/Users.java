package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 23.01.2017.
 */
public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;

    public Users(Users groups) {
        this.delegate=new HashSet<UserData>(groups.delegate);
    }

    public Users(){
        this.delegate=new HashSet<UserData>();
    }

    public Users(Collection<UserData> groups) {
        this.delegate=new HashSet<UserData>(groups);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

}
